package com.mx;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.UUID;

/**
 * @program: StuDemo
 * @description:
 * @author: maxin
 * @create: 2020-04-14 11:13
 * @Modified By:
 * @Version: 1.0
 **/
public class DataUpdater implements Watcher {
    private static String zooDataPath = "/MyConfig";
    ZooKeeper zk;
    public DataUpdater() throws IOException {
        zk = ZKUtils.createZKSession();
    }

    // updates the znode path /MyConfig every 5 seconds with a new UUID string.
    public void run() throws InterruptedException, KeeperException {
        while (true) {
            String uuid = UUID.randomUUID().toString();
            byte zoo_data[] = uuid.getBytes();
            zk.setData(zooDataPath, zoo_data, -1);
//            zk.delete(zooDataPath,zk.exists(zooDataPath,true).getVersion());
            try {
                Thread.sleep(2000); // Sleep for 5 secs
            } catch(InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) throws
            IOException, InterruptedException, KeeperException {
        DataUpdater dataUpdater = new DataUpdater();
        dataUpdater.run();
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.printf("\nEvent Received: %s", event.toString());
    }
}
