package mx.demo.flink;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;


/**
 * @description:
 * @author: maxin
 * @create: 2023/07/01
 **/
public class WordCount {
    public static void main(String[] args)throws Exception{
        // 创建执行环境
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        //从文件中读取数据
        // 获取路径
        String inputPath = "src/main/resources/hello.txt";
        // 从文件中读取数据
        DataSet<String> inputDataSet  = env.readTextFile(inputPath,"UTF-8");

        //  对数据集进行处理,按空格分词展开，装换成（word，1） 二元组进行统计
        DataSet<Tuple2<String,Integer>> resultSet  =  inputDataSet.flatMap(new MyFlatMapper())   // flatMap 映射
                .groupBy(0)    // 按照第一个位置的word 分组
                .sum(1);   // 将第二个位置上的数据求和
        resultSet.print();

    }
    // 自定义类，实现MyFlatMapper接口
    public static class MyFlatMapper implements FlatMapFunction<String, Tuple2<String,Integer>> {
        @Override
        public void flatMap(String s, Collector<Tuple2<String, Integer>> collector) throws Exception {
            // 分词
            String[] words = s.split(" ");
            // 遍历所有words,包装成二元组进行输出
            for (String word:words){
                // 没有返回类型，因此用out.collect
                collector.collect(new Tuple2<>(word,1));
            }
        }
    }
}
