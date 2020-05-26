package mx;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import java.util.Arrays;
import java.util.Properties;

/**
 * @program: KafkaTest
 * @description: test
 * @author: maxin
 * @create: 2020-03-11 12:44
 * @Modified By:
 * @Version: 1.0
 * offset = 60,key = 0,value ={other={address=newwork, interests=ball}, name=haha, age=10}
 **/
public class KafkaConsumer {

        public static void main(String[] args) {
            String topicName = "mx";
            String groupId = "mxgroup";
            //构造java.util.Properties对象
            Properties props = new Properties();
            // 必须指定属性。
            props.put("bootstrap.servers", "127.0.0.1:9092");
            // 必须指定属性。
            props.put("group.id", groupId);
            props.put("enable.auto.commit", "true");
            props.put("auto.commit.interval.ms", "1000");
            // 从最早的消息开始读取
            props.put("auto.offset.reset", "earliest");
            // 必须指定
            props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            // 必须指定
            props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

            // 使用创建的Properties实例构造consumer实例
            org.apache.kafka.clients.consumer.KafkaConsumer<String, String> consumer = new org.apache.kafka.clients.consumer.KafkaConsumer<String, String>(props);
//            KafkaConsumer consumer = new KafkaConsumer<String, String>(props);
            // 订阅topic。调用kafkaConsumer.subscribe方法订阅consumer group所需的topic列表
            consumer.subscribe(Arrays.asList(topicName));
            try {
                while (true) {
                    //循环调用kafkaConsumer.poll方法获取封装在ConsumerRecord的topic消息。
                    ConsumerRecords<String, String> records = consumer.poll(1000);
                    //获取到封装在ConsumerRecords消息以后，处理获取到ConsumerRecord对象。
                    for (ConsumerRecord<String, String> record : records) {
                        //简单的打印输出
                        System.out.println(
                                "offset = " + record.offset()
                                        + ",key = " + record.key()
                                        + ",value =" + record.value());
                    }
                }
            } catch (Exception e) {
                //关闭kafkaConsumer
                System.out.println("消息消费结束......");
                consumer.close();
            }
            System.out.println("关闭消费者......");
        }
    }

