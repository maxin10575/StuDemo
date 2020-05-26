package jspdemo.web;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SysdateTag extends SimpleTagSupport {
	//ʱ��ĸ�ʽ����Ĭ��ֵ
	//����ͨ��set�����޸���ֵ��
	private String format="yyyy/MM/dd HH:mm:ss";
	
	@Override
	public void doTag() throws JspException, IOException {
		//����������ʱ��
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String now = sdf.format(date);
		//��ʱ������������
		//�÷�����������JspContext,
		//�÷���ʵ�ʷ��ص���PageContext
		//PageContext extends JspContext
		PageContext ctx = (PageContext) getJspContext();
		JspWriter out = ctx.getOut();
		out.print(now);
		//�˴��������ܹرգ���ΪJSP�ϻ��������ı�ǩҪʹ���������
	}
	
	
	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
	
}
