package mx.demo.test2;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

import java.util.List;
import java.util.Map;

/**
 *
* Title: Test2Bolt
* Description:
* 统计单词出现的次数
* Version:1.0.0
* @date 2018年3月16日
 */
public class TestBolt2 extends BaseRichBolt{

	/**
	 *
	 */
	private static final long serialVersionUID = 4743224635827696343L;


	/**
	 * 保存单词和对应的计数
	 */
//	private HashMap<String, Integer> counts = null;
	private List<String> wordList = null;

	private static final String world_stream_id = "WORLD_STREAM_ID";
	private static final String nihao_stream_id = "NIHAO_STREAM_ID";
	/**
    * 在Bolt启动前执行，提供Bolt启动环境配置的入口
    * 一般对于不可序列化的对象进行实例化。
    * 注:如果是可以序列化的对象，那么最好是使用构造函数。
    */
	@SuppressWarnings("rawtypes")
	@Override
	public void prepare(Map map, TopologyContext arg1, OutputCollector collector) {
		System.out.println("TestBolt2-prepare初始化:"+map.get("test"));
//		this.counts=new HashMap<>();
//		this.wordList = new ArrayList<>();
	}

	/**
	 * execute()方法是Bolt实现的核心。
	 * 也就是执行方法，每次Bolt从流接收一个订阅的tuple，都会调用这个方法。
	 *
	 */
	@Override
	public void execute(Tuple tuple) {
		String msg0 = tuple.getString(0);
		String msg1 = tuple.getStringByField("haha");
		String msg2 = tuple.getStringByField("world");
		System.out.println("TestBolt2接收到数据为00000："+msg0);
		System.out.println("TestBolt2接收到数据为11111："+msg1);
		System.out.println("TestBolt2接收到数据22222为："+msg2);
		if (tuple.getSourceStreamId().equals(world_stream_id)) {
			System.out.println("TestBolt22222aaaa--tuple.getSourceStreamId()===" + tuple.getSourceStreamId());
		}
		if (tuple.getSourceStreamId().equals(nihao_stream_id)) {
			System.out.println("TestBolt22222bbbbb--tuple.getSourceStreamId()===" + tuple.getSourceStreamId());
		}
//		System.out.println("TestBolt2存储的单词有："+wordList.toString());
	}


	/**
     * cleanup是IBolt接口中定义,用于释放bolt占用的资源。
     * Storm在终止一个bolt之前会调用这个方法。
	 */
	@Override
	public void cleanup() {
//		System.out.println("TestBolt2===========开始显示单词============"+wordList.toString());
	/*	for (Map.Entry<String, Integer> entry : counts.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}*/
	    System.out.println("TestBolt2的资源释放");
	}

	/**
	 * 声明数据格式
	 */
	@Override
	public void declareOutputFields(OutputFieldsDeclarer arg0) {

	}
}
