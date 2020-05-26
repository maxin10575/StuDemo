package mx.interceptor;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * @program: KafkaTest
 * @description: 时间拦截器
 * @author: maxin
 * @create: 2020-03-11 14:17
 * @Modified By:
 * @Version: 1.0
 **/

public class TimeStampPrependerInterceptor implements ProducerInterceptor<String, String> {

    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        return new ProducerRecord<String, String>(record.topic(), record.partition(), record.timestamp(), record.key(),
                System.currentTimeMillis() + "," + record.value().toLowerCase());
    }

    @Override
    public void configure(Map<String, ?> arg0) {

    }

    @Override
    public void close() {

    }

    @Override
    public void onAcknowledgement(RecordMetadata arg0, Exception arg1) {

    }

}
