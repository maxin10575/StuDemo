package mx;/*
package mx;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;

import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;
import kafka.serializer.StringEncoder;

import kafka.tools.ConsoleConsumer;

import java.io.UnsupportedEncodingException;
import java.util.*;


public class KafkaConsumer<S, S1> {

*/
/*    public static void main(String []agrs){

        Properties props =new Properties();
        props.put("zookeeper.connect", "127.0.01:2181/kafka");
        //给消费者分组，这里分了一组 也可以不分
        props.put("group.id", "1");
        //读的信息，从开头开始读
        props.put("auto.offset.reset","smallest");
        //将props封装为消费者的配置对象
        ConsumerConfig config=new ConsumerConfig(props);
        //拿到一个消费者客户端
        ConsumerConnector consumer =Consumer.createJavaConsumerConnector(config);
        Map<String,Integer>topicCountMap=new HashMap<String,Integer>();
        // topic名称 ，第二个参数 是线程数量
        topicCountMap.put("mx", 2);
        //可以传入好多topic
        //topicCountMap.put("test2", 1);
        //获得消息流
        Map<String,List<KafkaStream<byte[],byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);//需要传一个Map参数

        //get map里面的topic  -test1  ，拿出来是test1里面的消息，为什么是t<KafkaStream<byte[],byte[]>，两个元素，因为卡夫卡KafkaStream包含消息和元数据。 比如一些消息管理数据
        List<KafkaStream<byte[],byte[]>>streams=consumerMap.get("mx");

        for(final KafkaStream<byte[],byte[]> kafkaStream : streams) {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    //遍历流kafkaStream对象
                    for (MessageAndMetadata<byte[], byte[]> mm : kafkaStream) {
                        //mm消息拿出来是序列化的，必须用String格式转换
                        String msg = new String(mm.message());
                        System.out.println(msg);
                    }
                }
            }).start();
        }
        }*//*

    private static final String TOPIC = "mx";

    public void exec() throws UnsupportedEncodingException {
        Properties props = new Properties();
        props.put("zookeeper.connect", "127.0.0.1/kafka");
        props.put("auto.offset.reset", "smallest");
        props.put("group.id", "mxgroup");
        props.put("enable.auto.commit", "true");
        props.put("zookeeper.sync.time.ms", "200");
        props.put("auto.commit.interval.ms", "1000");
        props.put("zookeeper.connection.timeout.ms", "10000");
//        props.put("client.id", "3");


        props.put("zookeeper.session.timeout.ms", "5000");
        props.put("rebalance.max.retries", "10");
        props.put("rebalance.backoff.ms", "1200");
//        ConsumerConnector consumer = Consumer.createJavaConsumerConnector(new ConsumerConfig(prop));
        ConsoleConsumer.ConsumerConfig consumerConfig = new kafka.consumer.ConsumerConfig(props);
        ConsumerConnector consumerConnector = kafka.consumer.Consumer.createJavaConsumerConnector(consumerConfig);

        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        int localConsumerCount = 1;
        topicCountMap.put(TOPIC, localConsumerCount);
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumerConnector.createMessageStreams(topicCountMap);
//            final KafkaStream<byte[], byte[]> kafkaStream = messageStreams.get(topic).get(0);
//        ConsumerIterator<byte[], byte[]> iterator = kafkaStream.iterator();
//        while (iterator.hasNext()) {
//            String msg = new String(iterator.next().message());
//            System.out.println("收到消息："+msg);
//        }


        List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(TOPIC);
        streams.stream().forEach(stream -> {
            ConsumerIterator<byte[], byte[]> it = stream.iterator();
            while (it.hasNext()) {
                System.out.println(new String(it.next().message()));
            }
        });
    }

    public static void main(String[] args) throws Exception {
        new KafkaConsumer<S, S1>().exec();
    }
}*/
