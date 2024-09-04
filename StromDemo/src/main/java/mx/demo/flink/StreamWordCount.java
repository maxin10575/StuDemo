package mx.demo.flink;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @description:
 * @author: maxin
 * @create: 2023/07/01
 **/
public class StreamWordCount {
    public static void main(String[] args) throws Exception {
        // 创建流处理执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        // 加载文件路径
        String inputFilePath = "src/main/resources/hello.txt";
        // 读取数据
        DataStream<String> fileData = env.readTextFile(inputFilePath);
        // 数据进行处理
        DataStream<Tuple2<String,Integer>> resultStream = fileData.flatMap(new WordCount.MyFlatMapper())
                .keyBy(0)
                .sum(1);
        resultStream.print();
        // 执行任务
        env.execute();
    }
}
