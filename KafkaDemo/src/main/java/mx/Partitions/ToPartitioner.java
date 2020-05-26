package mx.Partitions;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @program: KafkaTest
 * @description: 指定分区
 * @author: maxin
 * @create: 2020-03-11 14:29
 * @Modified By:
 * @Version: 1.0
 **/


public class ToPartitioner implements Partitioner {

    // 随机数
    private Random random;

    @Override
    public void configure(Map<String, ?> configs) {
        // 该方法实现必要资源的初始化工作
        random = new Random();
    }

    @Override
    public void close() {
        //該方法实现必要的清理工作
    }

    @Override
    public int partition(String topic, Object keyObject, byte[] keyBytes, Object value, byte[] valueBytes,
                         Cluster cluster) {

        // 获取到集群的元数据信息
        List<PartitionInfo> partitionsForTopic = cluster.availablePartitionsForTopic(topic);
        //遍历分区元数据信息
        Iterator<PartitionInfo> it = partitionsForTopic.iterator();
        while(it.hasNext()) {
            PartitionInfo partitionInfo = it.next();
            System.out.println("topic:： " + partitionInfo.topic() + " , partition分区信息： " + partitionInfo.partition()
                    + " ,leader信息： " + partitionInfo.leader() + " , replicas备份信息： " + partitionInfo.replicas());
        }
        // 获取到分区的数量
        int partitionCount = partitionsForTopic.size();

        // 获取到key
        String key = (String) keyObject;
        // 打印输出key信息
        System.out.println("key : " + key);

        // 获取到最后一个分区
        int lastPartition = 0;
        if(partitionCount > 1) {
            lastPartition = partitionCount - 1;
        }

        //如果key为空且不包含 "hh"，就将随机分发到除最后一个分区，否则，分发到随机分区。
        return key == null || key.isEmpty() || !key.contains("h1")
                ? lastPartition:random.nextInt(partitionCount);
    }

}