package test;

import java.awt.*;
import java.io.IOException;

/**
 * 子类覆盖父类含有throws异常抛出声明的方法时，
 * 子类对该方法throws定义的原则。
 * @author maxin
 *
 */
public class ExceptionRulesDemo {
	public void dosome()throws IOException,AWTException{

	}
}

class Son extends ExceptionRulesDemo{
	/*
	 * 可以一样
	 */
	//public void dosome()throws IOException,AWTException{
	//}


	/*
	 * 不再抛出任何异常
	 */
	//public void dosome(){
//	}


	/*
	 * 仅抛出父类方法抛出的部分异常
	 */
	//public void dosome()throws IOException{
	//}

	/*
	 * 抛出父类方法抛出的异常的子类异常
	 */
	//public   void dosome()throws IOException {
	//}

	/*
	 * 不能抛出额外异常
	 */
//	public void dosome()throws SQLException{
//	}

	/*
	 * 不能抛出父类方法抛出异常的父类异常
	 */
//	public void dosome()throws Exception{
//	}
}

