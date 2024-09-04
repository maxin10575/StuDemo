package ConcurrencyTest;

import lombok.Data;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * @program: StuDemo
 * @description:
 * @author:
 * @create: 2021-05-10 18:49
 * @Modified By:
 * @Version: 1.0
 **/
public class Test {

    public static void main(String[] args) {

        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student("小王", 12, "1"));

        studentList.add(new Student("小张", 14, "2"));

        studentList.add(new Student("小李", 15, "1"));
        studentList.add(new Student("小赵", 15, "2"));
        studentList.add(new Student("小钱", 15, "2"));

        studentList.add(new Student("小陈", 16, "1"));


        List<Student> collect = studentList.stream().collect(
                Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Student::getAge))), ArrayList::new));

        List<Student> userDeviceLogDtoListNew =  studentList.stream().
                collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(f -> f.getAge()+f.getPhone()))), ArrayList::new));


        System.out.println(collect);

        System.out.println(userDeviceLogDtoListNew);
    }



    @Data
    static class Student {

        Student(String name, Integer age, String phone) {
            this.age = age;
            this.name = name;
            this.phone = phone;

        }

        private Integer age;
        private String name;
        private String phone;

    }

    private static final Logger log = LoggerFactory.getLogger(Test.class);

    @GetMapping(value = "/roadFlowTest11")
    public void flushOrgData2ES() {
        int threadSize = 5;
//        List<String> roadidList = dataAnalyseDao.getRoadidList();
        List<String> roadidList = new ArrayList<>();
        long st = System.currentTimeMillis();
        final int everyThreadSize = roadidList.size() / threadSize + 1;
        final ExecutorService threadPool = new ThreadPoolExecutor(threadSize, threadSize + 5,
                0L, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(1024), new ThreadPoolExecutor.AbortPolicy());
        final CountDownLatch latch = new CountDownLatch(threadSize);
        for (int i = 0; i < threadSize; i++) {
            final int k = i;
            threadPool.execute(() -> {
                try {
                    String roadid = "";
                    Integer trafficFlow = null;
                    int flag = 0;
                    long start = System.currentTimeMillis();
                    log.info("=======第[{}]组开始=========", k);
                    for (int j = k * everyThreadSize; j <= everyThreadSize * (k + 1) - 1; j++) {
                        if (j <= roadidList.size() - 1) {
                            flag ++;
                            roadid = roadidList.get(j);
//                            trafficFlow = dataAnalyseDao.getTrafficFlow(roadid);
//                            dataAnalyseDao.updateRoadTrafficFlow(roadid, trafficFlow);
                            log.info("~~~~~~组：{}~~~更新第{}条数据，道路id为{},当前线程：{}",k,flag,roadidList.get(j),Thread.currentThread());
                        }
                    }
                    long end = System.currentTimeMillis();
                    log.info("=======第[{}]组结束=========耗时[{}]毫秒", k, (end - start));
                } catch (Exception e) {
                    log.info("同步数据异常，{}", e);
                } finally {
                    latch.countDown();
                }
            });
        }
        try {
            latch.await();
        } catch (Exception e) {
            log.error("CountDownLatch异常:{}", e);
        }
        threadPool.shutdown();
        long et = System.currentTimeMillis();
        log.error("=======同步数据结束=========耗时[{}]毫秒", (et - st));
    }


    /**
     * @param omHealthReportPoList
     * @param userIdMap
     * @desc: 分批保存奥美报告数据到本地
     * @author: maxin
     * @since: 2022/11/14
     */
    public Boolean saveReportData2iBed2(List<String> omHealthReportPoList, Map<String, Integer> userIdMap) {
        log.info("=====开始使用多线程同步奥美报告数据到本地=====");
        //200步长分组
        final int perSize = 1;
        final int taskNum = omHealthReportPoList.size() / perSize + 1;
        AtomicReference<Boolean> saveFlag = new AtomicReference<>(true);
//        ThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(taskNum);
        final ExecutorService threadPool = new ThreadPoolExecutor(8, 8 + 5,
                0L, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(1024), new ThreadPoolExecutor.AbortPolicy());
//        ThreadPoolExecutor executor = DefaultThreadPoolExecutor.newInstance("Ibed-RandarData-");
        CountDownLatch countDownLatch = new CountDownLatch(taskNum);
        for (int i = 0; i < taskNum; i++) {
            List<String> resultList;
            if ((i + 1) == taskNum) {
                int startIndex = (i * perSize);
                int endIndex = omHealthReportPoList.size();
                resultList = omHealthReportPoList.subList(startIndex, endIndex);
            } else {
                int startIndex = i * perSize;
                int endIndex = (i + 1) * perSize;
                resultList = omHealthReportPoList.subList(startIndex, endIndex);
            }
            if (CollectionUtils.isEmpty(resultList)) {
                countDownLatch.countDown();
                continue;
            }


            log.info("========第[{}]组开始=========", i + 1);
//            ImportTask task = new ImportTask(resultList, countDownLatch, userIdMap, i + 1, saveFlag);
//            executor.execute(task);
        }
        //主线程等待所有线程完成任务
        try {
            countDownLatch.await();
        } catch (Exception e) {
            log.error("同步奥美报告数据到本地,CountDownLatch异常:{}", e);
        }
        //所有线程完成任务后的一些业务
        //关闭线程池
//        executor.shutdown();
        log.info("=======同步奥美报告数据到本地结束=========");
        return saveFlag.get();
    }


    /**
     * @desc: 具体保存报告到本地逻辑
     * @author: maxin
     * @since: 2022/11/14
     */
     /*   class ImportTask implements Runnable {
        private final AtomicReference<Boolean> saveFlag;
        private final int num;
        private final List<OmHealthReportPo> list;
        private final CountDownLatch countDownLatch;
        private final Map<String, Integer> userIdMap;

        public ImportTask(List<OmHealthReportPo> list, CountDownLatch countDownLatch, Map<String, Integer> userIdMap, int num, AtomicReference<Boolean> saveFlag) {
            this.list = list;
            this.countDownLatch = countDownLatch;
            this.userIdMap = userIdMap;
            this.num = num;
            this.saveFlag = saveFlag;
        }

    @Override
        public void run() {
            if (null != list) {
                try {
                    long start = System.currentTimeMillis();
                    list.forEach(omHealthReportPo -> {
                        if (StringUtils.isNotBlank(omHealthReportPo.getSdate())) {
                            omHealthReportPo.setReportTime(omHealthReportPo.getSdate().substring(0, 10));
                        }
                        omHealthReportPo.setUserId(userIdMap.get(omHealthReportPo.getDeviceId()));
                    });
                    Boolean flag = omHealthReportDao.saveBatch(list);
                    saveFlag.set(saveFlag.get() && flag);
                    long end = System.currentTimeMillis();
                    if (!flag) {
                        List<String> sleepIdList = list.stream().map(OmHealthReportPo::getSleepId).collect(Collectors.toList());
                        log.error("******第[{}]组同步奥美报告数据到本地失败，sleepId：【{}】", num, JSON.toJSONString(sleepIdList));
                    } else {
                        log.info("========第[{}]组结束=========耗时[{}]毫秒====", num, (end - start));
                    }
                } catch (Exception e) {
                    List<String> sleepIdList = list.stream().map(OmHealthReportPo::getSleepId).collect(Collectors.toList());
                    log.error("******第[{}]组同步奥美报告数据到本地异常：{},sleepId:【{}】", e, JSON.toJSONString(sleepIdList));
                } finally {
                    countDownLatch.countDown();
                }
            }
        }
    }*/


}
