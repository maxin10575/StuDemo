package com.mx;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.List;

/**
 * @program: StuDemo
 * @description: 监听器
 * @author: maxin
 * @create: 2020-04-14 12:34
 * @Modified By:
 * @Version: 1.0
 **/
public class ClusterMonitor implements Runnable {
    private static String membershipRoot = "/Members";
    private final Watcher connectionWatcher;
    private final Watcher childrenWatcher;

    private ZooKeeper zk;
    boolean alive = true;

    public ClusterMonitor() throws IOException, InterruptedException, KeeperException {

        connectionWatcher = new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                if (event.getType() == Watcher.Event.EventType.None && event.getState() == Watcher.Event.KeeperState.SyncConnected) {
                    System.out.printf("\nEvent Received: %s", event.toString());
                }
            }
        };

        childrenWatcher = new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.printf("\nEvent Received: %s", event.toString());
                if (event.getType() == Event.EventType.NodeChildrenChanged) {
                    try {
                        //Get current list of child znode,
                        //reset the watch
                        List<String> children = zk.getChildren(membershipRoot, this);
                        wall("!!!Cluster Membership Change!!!");
                        wall("Members: " + children);
                    } catch (KeeperException e) {
                        throw new RuntimeException(e);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        alive = false;
                        throw new RuntimeException(e);
                    }
                }
            }
        };

        zk = ZKUtils.createZKSession();

        // Ensure the parent znode exists
        if (zk.exists(membershipRoot, false) == null) {
            zk.create(membershipRoot, "ClusterMonitorRoot".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }

        // Set a watch on the parent znode
        List<String> children = zk.getChildren(membershipRoot, childrenWatcher);
        System.err.println("Members: " + children);
    }


    public void wall(String message) {
        System.out.printf("\nMESSAGE: %s", message);
    }

    public void run() {
        try {
            synchronized (this) {
                while (alive) {
                    wait();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } finally {
           ZKUtils.closeZK(zk);
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        new ClusterMonitor().run();
    }
}
