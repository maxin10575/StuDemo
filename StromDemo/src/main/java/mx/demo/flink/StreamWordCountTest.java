package mx.demo.flink;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @description:
 * @author: maxin
 * @create: 2023/07/01
 **/
public class StreamWordCountTest {
    public static void main(String[] args) throws Exception{
        // 创建执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        // 从socket文本流读取数据
        DataStream<String>socketFile = env.socketTextStream("localhost",7777);
        // 基于流数据进行转换计算
        DataStream<Tuple2<String,Integer>> resultData = socketFile.flatMap(new WordCount.MyFlatMapper())
                .keyBy(0)
                .sum(1);
        // 打印
        resultData.print();
        // 执行任务
        env.execute();
    }
}

