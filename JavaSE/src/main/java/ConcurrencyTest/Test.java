package ConcurrencyTest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @program: StuDemo
 * @description:
 * @author:
 * @create: 2021-05-10 18:49
 * @Modified By:
 * @Version: 1.0
 **/
public class Test {

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
}
