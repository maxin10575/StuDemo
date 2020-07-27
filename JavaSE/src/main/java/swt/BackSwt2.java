package swt;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class BackSwt2 {

	static JFrame jframe;
	private JTextField datapath;
	private JTextField databackuppath;
	private JTextField datausrname;
	// private JTextField datausrpwd;
	private JTextField backupusrname;
	// private JTextField backupusrpwd;

	private JPasswordField datausrpwd;
	private JPasswordField backupusrpwd;

	// 编码
	private static String DEFAULTCHART = "UTF-8";
	private JTextField conport;
	private JTextField backconport;

	/**
	 * Launch the application.
	 */
	public static void startBackSwt2() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BackSwt2 window = new BackSwt2();
					window.jframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BackSwt2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		jframe = new JFrame();
		jframe.setResizable(true);
		jframe.setTitle("zookeeper\u6570\u636E\u6062\u590D");
		jframe.setBounds(400, 200, 450, 300);
//		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		jframe.getContentPane().setLayout(null);

		jframe.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				jframe.dispose();
			}
		});

		// 被备份主机连接信息
		final JTextField connecstr = new JTextField();
		connecstr.setBounds(104, 26, 135, 21);
		jframe.getContentPane().add(connecstr);
		connecstr.setColumns(10);

		// 目标主机连接信息
		final JTextField backupconnecstr = new JTextField();
		backupconnecstr.setBounds(104, 87, 135, 21);
		jframe.getContentPane().add(backupconnecstr);
		backupconnecstr.setColumns(10);

		// 被备份主机连接信息框说明
		JLabel connecstrlabel = new JLabel("\u5907\u4EFD\u4E3B\u673AIP\uFF1A");
		connecstrlabel.setBounds(17, 27, 77, 19);
		jframe.getContentPane().add(connecstrlabel);

		// 目标主机连接信息框说明
		JLabel backuplabel = new JLabel("ZK\u4E3B\u673AIP\uFF1A");
		backuplabel.setBounds(27, 87, 77, 21);
		jframe.getContentPane().add(backuplabel);

		// 需备份数据路径
		datapath = new JTextField();
		datapath.setBounds(104, 145, 317, 21);
		jframe.getContentPane().add(datapath);
		datapath.setColumns(10);

		JLabel lblzkdata = new JLabel("\u5907\u4EFD\u6570\u636E\u8DEF\u5F84\uFF1A");
		lblzkdata.setBounds(10, 145, 130, 21);
		jframe.getContentPane().add(lblzkdata);

		JLabel lblZk = new JLabel("ZK\u6570\u636E\u8DEF\u5F84\uFF1A");
		lblZk.setBounds(20, 176, 95, 21);
		jframe.getContentPane().add(lblZk);

		// 储存备份数据路径
		databackuppath = new JTextField();
		databackuppath.setColumns(10);
		databackuppath.setBounds(104, 176, 317, 21);
		jframe.getContentPane().add(databackuppath);

		JLabel label_1 = new JLabel("\u7528\u6237\u540D\uFF1A");
		label_1.setBounds(104, 57, 62, 20);
		jframe.getContentPane().add(label_1);

		// 需备份主机用户名
		datausrname = new JTextField();
		datausrname.setColumns(10);
		datausrname.setBounds(159, 57, 80, 21);
		jframe.getContentPane().add(datausrname);

		JLabel label_2 = new JLabel("\u5BC6\u7801\uFF1A");
		label_2.setBounds(265, 57, 46, 20);
		jframe.getContentPane().add(label_2);

		// 需备份主机用户密码
		datausrpwd = new JPasswordField();
		// datausrpwd = new JTextField();
		datausrpwd.setColumns(10);
		datausrpwd.setBounds(321, 57, 77, 20);
		jframe.getContentPane().add(datausrpwd);

		JLabel label_3 = new JLabel("\u7528\u6237\u540D\uFF1A");
		label_3.setBounds(104, 114, 62, 21);
		jframe.getContentPane().add(label_3);

		// 目标主机用户名
		backupusrname = new JTextField();
		backupusrname.setColumns(10);
		backupusrname.setBounds(159, 116, 80, 19);
		jframe.getContentPane().add(backupusrname);

		JLabel label_4 = new JLabel("\u5BC6\u7801\uFF1A");
		label_4.setBounds(265, 114, 46, 20);
		jframe.getContentPane().add(label_4);

		// 目标主机用户密码
		// backupusrpwd = new JTextField();
		backupusrpwd = new JPasswordField();
		backupusrpwd.setColumns(10);
		backupusrpwd.setBounds(321, 116, 77, 19);
		jframe.getContentPane().add(backupusrpwd);

		// 端口
		JLabel conportlabel = new JLabel("\u7AEF\u53E3\uFF1A");
		conportlabel.setBounds(266, 26, 62, 20);
		jframe.getContentPane().add(conportlabel);

		JLabel backconportlabel = new JLabel("\u7AEF\u53E3\uFF1A");
		backconportlabel.setBounds(265, 87, 46, 20);
		jframe.getContentPane().add(backconportlabel);

		conport = new JTextField();
		conport.setColumns(10);
		conport.setBounds(321, 25, 77, 19);
		jframe.getContentPane().add(conport);

		backconport = new JTextField();
		backconport.setColumns(10);
		backconport.setBounds(321, 88, 77, 19);
		jframe.getContentPane().add(backconport);

		// 开始备份按钮
		JButton backupButton = new JButton("\u5F00\u59CB\u6062\u590D");
		backupButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean connecstrflag = "".equals(connecstr.getText().trim());
				boolean backupconnecstrflag = "".equals(backupconnecstr.getText().trim());
				boolean datapathflag = "".equals(datapath.getText().trim());
				boolean databackuppathflag = "".equals(databackuppath.getText().trim());
				boolean datausrnameflag = "".equals(datausrname.getText().trim());
				boolean datausrpwdflag = "".equals(datausrpwd.getText().trim());
				boolean backupusrnameflag = "".equals(backupusrname.getText().trim());
				boolean backupusrpwdflag = "".equals(backupusrpwd.getText().trim());
				boolean conportflag = "".equals(conport.getText().trim());
				boolean backconportflag = "".equals(backconport.getText().trim());

				// 输入框不为空验证
				if (connecstrflag || backupconnecstrflag || datapathflag || databackuppathflag || datausrnameflag
						|| datausrpwdflag || backupusrnameflag || backupusrpwdflag || conportflag || backconportflag) {
					// 创建弹出框面板
					JDialog jdialog = new JDialog(jframe, "提示！");
					// 对话框大小
					jdialog.setSize(200, 100);
					// 对话框居中
					jdialog.setLocationRelativeTo(jframe);
					jdialog.setVisible(true);
					// 设置对话框显示信息
					jdialog.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
					jdialog.getContentPane().add(new JLabel("请输入完整信息!"));
					return;
				}

				// 验证备份主机与目标主机连接信息是否正确
				Connection connect = RemoteExecuteCommand.login(connecstr.getText().trim(),
						datausrname.getText().trim(), datausrpwd.getText().trim(), conport.getText().trim());
				Connection connect1 = RemoteExecuteCommand.login1(backupconnecstr.getText().trim(),
						backupusrname.getText().trim(), backupusrpwd.getText().trim(), backconport.getText().trim());

				// ZK主机连接失败弹出提醒
				if (connect == null) {
					// 创建弹出框面板
					JDialog jdialog = new JDialog(jframe, "提示！");
					// 对话框大小
					jdialog.setSize(200, 100);
					// 对话框居中
					jdialog.setLocationRelativeTo(jframe);
					jdialog.setVisible(true);
					// 设置对话框显示信息
					jdialog.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
					jdialog.getContentPane().add(new JLabel("备份主机连接失败！"));
					return;
				}

				// 备份主机连接失败弹出提醒
				if (connect1 == null) {
					// 创建弹出框面板
					JDialog jdialog = new JDialog(jframe, "提示！");
					// 对话框大小
					jdialog.setSize(200, 100);
					// 对话框居中
					jdialog.setLocationRelativeTo(jframe);
					jdialog.setVisible(true);// 可见
					// 设置对话框显示信息
					jdialog.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
					jdialog.getContentPane().add(new JLabel("ZK主机连接失败！"));
					return;
				}

				try {
					// 备份数据路径测试
					Session session = connect.openSession();
					String cmdtestpath = "cd " + datapath.getText().trim();
					session.execCommand(cmdtestpath);
					String result = RemoteExecuteCommand.processStdout(session.getStderr(), DEFAULTCHART);
					if ("pathisfalse".equals(result)) {
						System.out.println("备份数据路径错误！");
						// 创建弹出框面板
						JDialog jdialog = new JDialog(jframe, "提示！");
						// 对话框大小
						jdialog.setSize(200, 100);
						// 对话框居中
						jdialog.setLocationRelativeTo(jframe);
						jdialog.setVisible(true);
						// 设置对话框显示信息
						jdialog.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
						jdialog.getContentPane().add(new JLabel("备份数据路径错误！"));
						session.close();
						return;
					}

					// ZK主机数据路径测试
					Session session1 = connect1.openSession();
					String cmdtestpath1 = "cd " + databackuppath.getText().trim();
					session1.execCommand(cmdtestpath1);
					String result1 = RemoteExecuteCommand.processStdout(session1.getStderr(), DEFAULTCHART);
					if ("pathisfalse".equals(result1)) {
						System.out.println("ZK数据路径错误！");
						// 创建弹出框面板
						JDialog jdialog = new JDialog(jframe, "提示！");
						// 对话框大小
						jdialog.setSize(200, 100);
						// 对话框居中
						jdialog.setLocationRelativeTo(jframe);
						jdialog.setVisible(true);
						// 设置对话框显示信息
						jdialog.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
						jdialog.getContentPane().add(new JLabel("ZK数据路径错误！"));
						session1.close();
						return;
					}

					// 新建会话
					Session session2 = connect.openSession();
					String cmd = "scp -r " + datapath.getText().trim() + " " + backupconnecstr.getText().trim() + ":"
							+ databackuppath.getText().trim();
					System.out.println("执行命令：" + cmd);
					session2.execCommand(cmd);
					session2.close();

					// 创建弹出框面板
					JDialog jdialog = new JDialog(jframe, "提示！");
					// 对话框大小
					jdialog.setSize(200, 100);
					// 对话框居中
					jdialog.setLocationRelativeTo(jframe);
					jdialog.setVisible(true);
					// 设置对话框显示信息
					jdialog.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
					jdialog.getContentPane().add(new JLabel("zk数据恢复成功！"));
					connect.close();

				} catch (Exception e2) {
					e2.printStackTrace();
				} finally {

				}
			}
		});
		backupButton.setBounds(177, 217, 93, 23);
		jframe.getContentPane().add(backupButton);

	}

	public static String getDEFAULTCHART() {
		return DEFAULTCHART;
	}

	public static void setDEFAULTCHART(String dEFAULTCHART) {
		DEFAULTCHART = dEFAULTCHART;
	}
}
