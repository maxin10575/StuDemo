package test;

import java.awt.*;
import java.io.IOException;

/**
 * ���า�Ǹ��ຬ��throws�쳣�׳������ķ���ʱ��
 * ����Ը÷���throws�����ԭ��
 * @author maxin
 *
 */
public class ExceptionRulesDemo {
	public void dosome()throws IOException,AWTException{

	}
}

class Son extends ExceptionRulesDemo{
	/*
	 * ����һ��
	 */
	//public void dosome()throws IOException,AWTException{
	//}


	/*
	 * �����׳��κ��쳣
	 */
	//public void dosome(){
//	}


	/*
	 * ���׳����෽���׳��Ĳ����쳣
	 */
	//public void dosome()throws IOException{
	//}

	/*
	 * �׳����෽���׳����쳣�������쳣
	 */
	//public   void dosome()throws IOException {
	//}

	/*
	 * �����׳������쳣
	 */
//	public void dosome()throws SQLException{
//	}

	/*
	 * �����׳����෽���׳��쳣�ĸ����쳣
	 */
//	public void dosome()throws Exception{
//	}
}

