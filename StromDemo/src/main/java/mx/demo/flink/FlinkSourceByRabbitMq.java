package mx.demo.flink;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.rabbitmq.common.RMQConnectionConfig;

/**
 * @description:
 * @author: maxin
 * @create: 2023/07/01
 **/
public class FlinkSourceByRabbitMq {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        RMQConnectionConfig connectionConfig = new RMQConnectionConfig.Builder()
                .setHost("rmq-test.wonlycloud.com")
                .setPort(5672)
                .setUserName("scene_java")
                .setPassword("Wl2016822")
                .setVirtualHost("/")
                .build();
//        DataStreamSource resultData = env.addSource(new FlinkRabbitMqSource(connectionConfig,
//                "dasToIbed_que",
//                "dasToIbed_ex"));
        DataStream resultData = env.addSource(new FlinkRabbitMqSource(connectionConfig,
                "dasToIbed_que",
                "dasToIbed_ex"));
        resultData.print();
        env.execute();
    }
}
