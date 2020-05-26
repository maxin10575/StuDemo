package mx.CustomSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.istack.internal.logging.Logger;
import org.apache.kafka.common.serialization.Serializer;

import java.io.IOException;
import java.util.Map;


/**
 * @program: KafkaTest
 * @description: 自定义序列化对象
 * @author: maxin
 * @create: 2020-03-11 14:08
 * @Modified By:
 * @Version: 1.0
 **/

public class UserSerializer implements Serializer<Object> {

    private Logger logger = Logger.getLogger(UserSerializer.class);

    private ObjectMapper objectMapper;

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        objectMapper = new ObjectMapper();
    }

    @Override
    public byte[] serialize(String topic, Object data) {
        byte[] ret = null;
        try {
            ret = objectMapper.writeValueAsString(data).getBytes("utf-8");
        } catch (IOException e) {
            e.printStackTrace();
            logger.warning("自定义序列化失败告警.....请紧急处理", e);
        }
        return ret;
    }

}