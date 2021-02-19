package mx.demo.test2;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.Map;


/**
 * Title: TestBolt
 * Description:
 * 对单词进行分割
 * Version:1.0.0
 *
 * @date 2018年3月16日
 */
public class TestBolt1 extends BaseRichBolt {

    /**
     *
     */
    private static final long serialVersionUID = 4743224635827696343L;

    private OutputCollector collector;

    private static final String hellow_stream_id = "HELLOW_STREAM_ID";
    private static final String world_stream_id = "WORLD_STREAM_ID";

    /**
     * 在Bolt启动前执行，提供Bolt启动环境配置的入口
     * 一般对于不可序列化的对象进行实例化。
     * 注:如果是可以序列化的对象，那么最好是使用构造函数。
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void prepare(Map map, TopologyContext arg1, OutputCollector collector) {
        System.out.println("TestBolt1-prepare初始化:" + map.get("test"));
        this.collector = collector;
    }

    /**
     * execute()方法是Bolt实现的核心。
     * 也就是执行方法，每次Bolt从流接收一个订阅的tuple，都会调用这个方法。
     */
    @Override
    public void execute(Tuple tuple) {
        //一样
//        String msg = tuple.getStringByField("origin");
        String msg1 = tuple.getString(0);
//        System.out.println("TestBolt1---接收数据为:" + msg);
        System.out.println("TestBolt1---接收数据为1111:" + msg1);
//        String[] words = msg.toLowerCase().split(",");
//        for (String word : words) {
//        this.collector.emit(new Values("word"));//向下一个bolt发射数据
//        }
//        this.collector.emit(new Values("hello"));//向下一个bolt发射数据


        collector.emit(hellow_stream_id, new Values("3", "***hello***"));
        System.out.println("TestBolt1向HELLOW_STREAM_ID发射数据：" + new Values("***3hello***"));

        collector.emit(world_stream_id, new Values("2", "***world***"));
        System.out.println("TestBolt1向hWORLD_STREAM_ID发射数据：" + new Values("***3world***"));

    }

    /**
     * 声明数据格式
     */
    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declareStream(hellow_stream_id, new Fields("id", "hellow"));
        declarer.declareStream(world_stream_id, new Fields("id", "world"));

/*		Fields outputFields = new Fields(
			"word","hello"
		);
		declarer.declare(outputFields);*/
//		declarer.declare(new Fields("word"));
    }

    /**
     * cleanup是IBolt接口中定义,用于释放bolt占用的资源。
     * Storm在终止一个bolt之前会调用这个方法。
     */
    @Override
    public void cleanup() {
        System.out.println("TestBolt1--的资源释放");
    }
}
