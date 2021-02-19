package mx.demo.test2;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.topology.TopologyBuilder;

/**
 *
* Title: App
* storm测试
* Version:1.0.0
* @author mx
* @date 2018年3月6日
 */
public class App {

	private static final String test_spout="test_spout";
	private static final String test_bolt1="test_bolt1";
	private static final String test_bolt2="test_bolt2";
	private static final String test_bolt3="test_bolt3";

	private static final String hellowStreamId = "HELLOW_STREAM_ID";
	private static final String worldField = "WORLD_STREAM_ID";
	private static final String nihao_stream_id = "NIHAO_STREAM_ID";

	public static void main(String[] args)  {
		//定义一个拓扑
		TopologyBuilder builder=new TopologyBuilder();
		//设置1个Executeor(线程)，默认一个
		builder.setSpout(test_spout, new TestSpout(),1);
		//shuffleGrouping:表示是随机分组
		builder.setBolt(test_bolt1, new TestBolt1(),1).setNumTasks(1).shuffleGrouping(test_spout);
		//fieldsGrouping:表示是按字段分组
		builder.setBolt(test_bolt3, new TestBolt3(),1).setNumTasks(1).shuffleGrouping(test_spout).shuffleGrouping(test_bolt1,hellowStreamId);
		builder.setBolt(test_bolt2, new TestBolt2(),1).setNumTasks(1).shuffleGrouping(test_bolt1,worldField).shuffleGrouping(test_bolt3,nihao_stream_id);

		Config conf = new Config();
		//设置启动4个Worker
		conf.setNumWorkers(4);
		// 设置一个ack线程
		conf.setNumAckers(1);
		conf.setDebug(true); // 设置打印所有发送的消息及系统消息
		conf.put("test", "test");
		try{
		  //运行拓扑
	       if(args !=null&&args.length>0){ //有参数时，表示向集群提交作业，并把第一个参数当做topology名称
	       	 System.out.println("运行远程模式");
			 StormSubmitter.submitTopology(args[0], conf, builder.createTopology());
	      } else{//没有参数时，本地提交
	        //启动本地模式
	     	System.out.println("运行本地模式");
	        LocalCluster cluster = new LocalCluster();
	        cluster.submitTopology("Word-counts" ,conf,  builder.createTopology() );
	        Thread.sleep(600000);
//	        //关闭本地集群
	        cluster.shutdown();
	      }
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
