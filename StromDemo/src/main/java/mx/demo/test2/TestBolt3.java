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
public class TestBolt3 extends BaseRichBolt {

    /**
     *
     */
    private static final long serialVersionUID = 4743224635827696343L;

    private OutputCollector collector;

    private static final String hellow_stream_id = "HELLOW_STREAM_ID";
    private static final String nihao_stream_id = "NIHAO_STREAM_ID";
    /**
     * 在Bolt启动前执行，提供Bolt启动环境配置的入口
     * 一般对于不可序列化的对象进行实例化。
     * 注:如果是可以序列化的对象，那么最好是使用构造函数。
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void prepare(Map map, TopologyContext arg1, OutputCollector collector) {
        System.out.println("TestBolt3-prepare:" + map.get("test"));
        this.collector = collector;
    }

    /**
     * execute()方法是Bolt实现的核心。
     * 也就是执行方法，每次Bolt从流接收一个订阅的tuple，都会调用这个方法。
     */
    @Override
    public void execute(Tuple tuple) {
        if (tuple.getSourceStreamId().equals(hellow_stream_id)) {
            System.out.println("TestBolt33333--tuple.getSourceStreamId()===" + tuple.getSourceStreamId());
        }
        String msg3 = tuple.getStringByField("origin");
        String msg = tuple.getString(0);
//        String msg1 = tuple.getStringByField("id");
        String msg2 = tuple.getStringByField("hellow");

        System.out.println("TestBolt3-接收到的数据是:" + msg);
//        System.out.println("TestBolt3-接收到的数据1111是:" + msg1);
        System.out.println("TestBolt3-接收到的数据222是:" + msg2);
        System.out.println("TestBolt3-接收到的数据3333是:" + msg3);

        System.out.println("TestBolt3-向下一个bolt2发射数据" + "123456");
        this.collector.emit(nihao_stream_id,new Values("123456"));
    }

    /**
     * 声明数据格式
     */
    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declareStream(nihao_stream_id, new Fields("nihao"));
        declarer.declare(new Fields("haha"));
    }

    /**
     * cleanup是IBolt接口中定义,用于释放bolt占用的资源。
     * Storm在终止一个bolt之前会调用这个方法。
     */
    @Override
    public void cleanup() {
        System.out.println("TestBolt3的资源释放");
    }
}
