/*
package com.ai.aif.zk;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.jcraft.jsch.SftpProgressMonitor;

public class ChannelSftpSingleton {
	public static void main(String[] args) {
		try{
		Properties prop = new Properties();
		InputStream inputStream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("config.properties");
		prop.load(inputStream);
		// time = Long.valueOf(prop.getProperty("TIME"));
		// zk所在主机信息
		final String ip = prop.getProperty("IP");
		final String port = prop.getProperty("PORT");
		final String zkdatapath = prop.getProperty("ZKDATAPATH");
		String usrname = prop.getProperty("USRNAME");
		final String usrpwd = prop.getProperty("USRPWD");

		// 备份主机信息
		final String backupip = prop.getProperty("BACKUPIP");
		final String backupport = prop.getProperty("BACKUPPORT");
		final String backupdatapath = prop.getProperty("BACKUPDATAPATH");
		final String backusrname = prop.getProperty("BACKUPUSRNAME");
		final String backusrpwd = prop.getProperty("BACKUPUSRPWD");
		String timeoutstr = prop.getProperty("TIMEOUT");

		// 命令
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
		String dateStr = simpleDateFormat.format(new Date());
		final String cmd = "scp -r " + zkdatapath + " " + backupip + ":" + backupdatapath + "version-2_" + dateStr;
		final String cmd1 = "tar -cvf " + backupdatapath + "version-2_" + dateStr + ".tar.gz " + backupdatapath
				+ "version-2_" + dateStr;
		final String cmd2 = "rm -rf " + backupdatapath + "version-2_" + dateStr;
		final String cmd3 = cmd1 + ";" + cmd2;
		int portzk = Integer.valueOf(port);
		int backport = Integer.valueOf(backupport);
		int timeout = Integer.valueOf(timeoutstr);
		
		Session session = null;
		ChannelSftp chSftp = getChannel(usrname,ip,portzk,usrpwd,timeout);
		chSftp.put(zkdatapath, backupdatapath, ChannelSftp.OVERWRITE);
		chSftp.quit();
//		if (session != null) {
//			session.disconnect();
//		}
//		closeChannel(chSftp, session);
		
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	//
	*/
/**
	 * 获得SFTP Channel
	 * 
	 * @return ChannelSftp Instance
	 * @throws JSchException
	 *//*

	public static ChannelSftp getChannel(String usrname,String ip,int portzk,String usrpwd,int timeout) throws JSchException {
		// 创建JSch对象
		JSch jsch1 = new JSch();
		// 根据用户名，主机ip，端口获取一个Session对象
		Session session = jsch1.getSession(usrname, ip, portzk);
		// logger.info("Session created.");
		if (usrpwd != null) {
			// 设置密码
			session.setPassword(usrpwd);
		}
		Properties configTemp = new Properties();
		configTemp.put("StrictHostKeyChecking", "no");
		// 为Session对象设置properties
		session.setConfig(configTemp);
		// 设置timeout时间
		session.setTimeout(timeout);
		session.connect();
		// 通过Session建立链接
		// 打开SFTP通道
		Channel channel = session.openChannel("sftp");
		// 建立SFTP通道的连接
		channel.connect();
		System.out.println("Connected successfully to ftpHost = " + ip + ",as ftpUserName = " + usrname
				+ ", returning: " + channel);
		return (ChannelSftp) channel;
	}

	*/
/**
	 * 断开SFTP Channel、Session连接
	 * 
	 * @throws Exception
	 *//*

	public static void closeChannel(ChannelSftp channel,Session session) throws Exception {
		if (channel != null) {
			channel.disconnect();
		}
		System.out.println("disconnected SFTP successfully!");
	}

}
*/
