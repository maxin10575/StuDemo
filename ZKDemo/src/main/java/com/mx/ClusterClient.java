package com.mx;

import org.apache.zookeeper.*;
import org.junit.Test;

import java.io.IOException;
import java.lang.management.ManagementFactory;

/**
 * @program: StuDemo
 * @description: 客户端 同时起多个
 * @author: maxin
 * @create: 2020-04-14 12:35
 * @Modified By:
 * @Version: 1.0
 **/
public class ClusterClient implements Watcher, Runnable {
    private static String membershipRoot = "/Members";
    ZooKeeper zk;
    public ClusterClient(String hostPort, Long pid) {
        String processId = pid.toString();
        try {
            zk = new ZooKeeper(hostPort, 2000, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (zk != null) {
            try {
                zk.create(membershipRoot + '/' + processId, processId.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            } catch (
                    KeeperException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void close() {
        try {
            zk.close();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.printf("\nEvent Received: %s", event.toString());
    }

    public void run() {
        try {
            synchronized (this) {
                while (true) {
                    wait();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } finally {
            this.close();
        }
    }

    public static void main(String[] args) {
        String hostPort = "127.0.0.1:2181";
        //Get the process id
        //是Java 虚拟机的运行时系统的管理接口。使用它可以获取正在运行的 Java 虚拟机等信息，包括获取PID。
        String name = ManagementFactory.getRuntimeMXBean().getName();
        int index = name.indexOf('@');
        Long processId = Long.parseLong(name.substring(0, index));
        new ClusterClient(hostPort, processId).run();
        //Get the process id
    }
}
