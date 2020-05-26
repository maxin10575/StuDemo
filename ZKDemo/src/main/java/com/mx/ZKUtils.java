package com.mx;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: StuDemo
 * @description: connect close
 * @author: maxin
 * @create: 2020-04-14 10:00
 * @Modified By:
 * @Version: 1.0
 **/

public class ZKUtils {
    /** 
    * @Description: 创建连接 
    * @Param:  * @param  
    * @return: org.apache.zookeeper.ZooKeeper
    * @Author: maxin
    * @Date: 2020/4/14
    * @Modified By:
    * @Version: 1.0.0
    */
    public static ZooKeeper createZKSession() {
        // 例如，10.0.0.1:2001,10.0.0.2:2002,10.0.0.3:2003表示三个节点的ZooKeeper
        String hostPort = "localhost:2181";
        String zpath = "/";
        List<String> zooChildren = new ArrayList<String>();
        ZooKeeper zk = null;
        try {
            zk = new ZooKeeper(hostPort, 20000, null);
           /* if (zk != null) {
                zooChildren = zk.getChildren(zpath, false);
                System.out.println("Znodes of '/': ");
                for (String child : zooChildren) {
                    System.out.println(child);
                }
            }*/
        } catch (IOException e) {
            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (KeeperException e) {
//            e.printStackTrace();
        }
        return zk;
    }


    public static void main(String[] args) {
        new ClusterClient("127.0.0.1:2181", 2L).run();
    }


    /** 
    * @Description: 关闭zk连接 
    * @Param:  * @param zooKeeper
    * @return: void
    * @Author: maxin
    * @Date: 2020/4/14
    * @Modified By:
    * @Version: 1.0.0
    */
    public static void closeZK(ZooKeeper zooKeeper){
        try {
            zooKeeper.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

