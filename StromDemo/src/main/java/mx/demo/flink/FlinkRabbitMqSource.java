package mx.demo.flink;

import com.rabbitmq.client.AMQP;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.connectors.rabbitmq.RMQSource;
import org.apache.flink.streaming.connectors.rabbitmq.common.RMQConnectionConfig;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @description:
 * @author: maxin
 * @create: 2023/07/01
 **/
public class FlinkRabbitMqSource extends RMQSource {

    public String exchangeName;

    /**
     * @param rmqConnectionConfig rabbitMQ的连接器配置（ip 端口 账号 密码等）
     * @param queueName           监听的队列名
     * @param exchangeName        队列绑定的交换机名
     *                            注：此设置后如果监听的队列不存在，其会自动创建，并与指定的交换机进行绑定
     */
    public FlinkRabbitMqSource(RMQConnectionConfig rmqConnectionConfig, String queueName,
                               String exchangeName) {
        super(rmqConnectionConfig, queueName, new SimpleStringSchema(StandardCharsets.UTF_8));
        this.exchangeName = exchangeName;
    }

    /**
     * 设置消息队列 队列绑定到交换机
     *
     * @throws IOException
     */
    @Override
    protected void setupQueue() throws IOException {
        AMQP.Queue.DeclareOk result = channel.queueDeclare(queueName, true, false, false, null);
        channel.queueBind(result.getQueue(), exchangeName, "*");
    }
}
