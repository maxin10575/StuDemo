package com.ai.aif.zk;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import ch.ethz.ssh2.ChannelCondition;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;

public class ZkAutoBackup {
    private static Connection conn;
    private static Connection conn1;

    private static long timeout = 1200000L;

    private static long intervalTime = 60 * 60 * 1000 * 24L;

    // private static long time;

    public static void main(String[] args) throws IOException {
        Properties prop = new Properties();
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties");
        prop.load(in);

        // time = Long.valueOf(prop.getProperty("TIME"));
        // zk所在主机信息
        final String ip = prop.getProperty("IP");
        final String port = prop.getProperty("PORT");
        final String zkdatapath = prop.getProperty("ZKDATAPATH");
        final String usrname = prop.getProperty("USRNAME");
        final String usrpwd = prop.getProperty("USRPWD");

        // 备份主机信息
        final String backupip = prop.getProperty("BACKUPIP");
        final String backupport = prop.getProperty("BACKUPPORT");
        final String backupdatapath = prop.getProperty("BACKUPDATAPATH");
        final String backusrname = prop.getProperty("BACKUPUSRNAME");
        final String backusrpwd = prop.getProperty("BACKUPUSRPWD");

        try {
            timeout = Integer.valueOf(prop.getProperty("TIMEOUT"));
        } catch (NumberFormatException e1) {
            System.out.println("超时参数配置错误！！！使用默认值" + 1200000);
        }

        try {
            intervalTime = Integer.valueOf(prop.getProperty("INTERVALTINE"));
        } catch (NumberFormatException e1) {
            System.out.println("超时参数配置错误！！！使用默认值" + 60 * 60 * 1000 * 24L);
        }

        // zk主机认证
        try {
            conn = new Connection(ip, Integer.valueOf(port));
            conn.connect();// 连接
            boolean flg = conn.authenticateWithPassword(usrname, usrpwd);
            if (flg) {
                System.out.println("zk主机认证成功！");
            } else {
                System.out.println("zk主机认证失败！");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 备份主机认证
        try {
            conn1 = new Connection(backupip, Integer.valueOf(backupport));
            conn1.connect();// 连接
            boolean flg = conn1.authenticateWithPassword(backusrname, backusrpwd);
            if (flg) {
                System.out.println("备份主机认证成功！");
            } else {
                System.out.println("备份主机认证失败！");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //定时任务
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleWithFixedDelay(new Runnable() {

            @Override
            public void run() {
                try {

                    // 获得Zookeeper的log文件路径
                    int zkport = Integer.valueOf(port);
                    String cmdchoosefile = "ls -t " + zkdatapath + "/log.* | head -n 1";
                    String logfilepath = zkUtil.exec(ip, usrname, usrpwd, zkport, cmdchoosefile);
                    System.out.println("需要备份的Zookeeper的log文件路径：" + logfilepath);
                    // 获得zookeeper的snapshot文件路径
                    String cmdchoosesnapshot = "ls -t " + zkdatapath + "/snapshot.* | head -n 1";
                    String snapshotfilepath = zkUtil.exec(ip, usrname, usrpwd, zkport, cmdchoosesnapshot);
                    System.out.println("需要备份的Zookeeper的snapshot文件路径：" + snapshotfilepath);

                    // 命令
                    // 在备份目录下创建zkdata-日期文件夹
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
                    String dateStr = simpleDateFormat.format(new Date());
                    String zkdatadir = "zkdata-" + dateStr;
                    String cmdfiledirmkdir = "mkdir -p " + zkdatapath + "/" + zkdatadir;

                    String snapshotfile = snapshotfilepath.trim();
                    // String cmdscpsnapshot = "scp -r " + snapshotfile + " " +
                    // backupip + ":" + backupdatapath+zkdatadir;
                    String cmdcpsnapshot = "cp " + snapshotfile + " " + zkdatapath + "/" + zkdatadir;

                    String logfile = logfilepath.trim();
                    // String cmdscplog = "scp -r " + logfile + " " + backupip +
                    // ":" + backupdatapath+zkdatadir;
                    String cmdcplog = "cp " + logfile + " " + zkdatapath + "/" + zkdatadir;

                    String cmdfiledirtar = "tar -cvf " + zkdatapath + "/" + zkdatadir + ".tar.gz " + zkdatapath + "/"
                            + zkdatadir;

                    String cmdscp = " scp -r " + zkdatapath + "/" + zkdatadir + ".tar.gz " + backupip + ":"
                            + backupdatapath;

                    String cmdfiledirdel = "rm -rf " + zkdatapath + "/" + zkdatadir + ".tar.gz ";
                    String cmdfiletardel = "rm -rf " + zkdatapath + "/" + zkdatadir;
                    Session session = conn.openSession();
                    Session session1 = conn1.openSession();
                    // 建立虚拟终端
                    session.requestPTY("bash");
                    session1.requestPTY("bash");
                    // 打开shell
                    session.startShell();
                    session1.startShell();
                    // 准备输入命令
                    PrintWriter out = new PrintWriter(session.getStdin());
                    PrintWriter out1 = new PrintWriter(session.getStdin());
                    // 输入待执行命令
                    out.println(cmdfiledirmkdir);
                    out.println(cmdcpsnapshot);
                    out.println(cmdcplog);
                    out.println(cmdfiledirtar);
                    out.println(cmdscp);
                    out.println(cmdfiledirdel);
                    out.println(cmdfiletardel);
                    out.println("exit");
                    out1.println("exit");
                    out.close();
                    out1.close();

                    // 7. 等待，除非1.连接关闭；2.输出数据传送完毕；3.进程状态为退出；4.超时
                    session.waitForCondition(
                            ChannelCondition.CLOSED | ChannelCondition.EOF | ChannelCondition.EXIT_STATUS, timeout);
                    System.out.println("执行命令1：" + cmdfiledirmkdir);
                    System.out.println("执行命令2：" + cmdcpsnapshot);
                    System.out.println("执行命令3：" + cmdcplog);
                    System.out.println("执行命令4：" + cmdfiledirtar);
                    System.out.println("执行命令5：" + cmdfiledirtar);
                    System.out.println("执行命令6：" + cmdscp);
                    System.out.println("执行命令7：" + cmdfiledirdel);
                    System.out.println("执行命令8：" + cmdfiletardel);
                    System.out.println("备份成功！");

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, 0, intervalTime, TimeUnit.MILLISECONDS);

    }
}
