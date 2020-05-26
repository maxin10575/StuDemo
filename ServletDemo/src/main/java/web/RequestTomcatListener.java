package web;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class RequestTomcatListener implements ServletRequestListener {

	// tomcat������requestʱ�Զ����ø÷�����
	// ֪ͨ������request�����ˡ�
	@Override
	public void requestDestroyed(ServletRequestEvent e) {
		System.out.println("����request");
	}

	// tomcat�ڴ���requestʱ���Զ����ø÷�����
	// ֪ͨ������request��ʼ���ˣ�������������
	// ������ص�ҵ�񡣲������¼�������tomcat
	// ��������ֵ�����롣
	public void requestInitialized(ServletRequestEvent e) {
		System.out.println("��ʼ��request");
		System.out.println(e.getServletRequest());
	}

}
