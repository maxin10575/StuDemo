package com.mx.example.tree;

import java.util.*;

/**
 * TODO:多叉树,并以json格式输出多叉树
 *
 * @author dangzw
 * @version 1.0, 2019/1/10/14:00
 */
public class MultiTree {
    public static void main(String[] args) {
        List dataList = VirtualDataGenerator.getVirtualResult();
        //节点列表映射表，用于存储临时节点对象
        HashMap nodeList = new HashMap();
        //根节点
        Node root = null;
        //将结果集存入映射表，用于借助映射表构造多叉树
        for (Iterator it = dataList.iterator(); it.hasNext(); ) {
            Map dataRecode = (Map) it.next();
            Node node = new Node();
            node.id = (String) dataRecode.get("id");
            node.text = (String) dataRecode.get("text");
            node.parentId = (String) dataRecode.get("parentId");
            nodeList.put(node.id, node);
        }
        //构造无序多叉树
        Set entrySet = nodeList.entrySet();
        for (Iterator it = entrySet.iterator(); it.hasNext(); ) {
            Node node = (Node) ((Map.Entry) it.next()).getValue();
            if (node.parentId == null || node.parentId.equals("")) {
                root = node;
            } else {
                ((Node) nodeList.get(node.parentId)).addChild(node);
            }
        }
        System.out.println(root);
        root.sortChildren();
        System.out.println(root);
    }
}

//节点类
class Node {
    /**
     * 节点编号
     */
    public String id;
    /**
     * 节点内容
     */
    public String text;
    /**
     * 父节点编号
     */
    public String parentId;
    /**
     * 孩子结点列表
     */
    private List children = new ArrayList();

    //添加孩子结点
    public void addChild(Node node) {
        children.add(node);
    }

    //先序遍历，拼接json字符串
    public String toString() {
        String result = "{" + "id: '" + id + "'" + ",text: '" + text + "'";
        if (children.size() != 0) {
            result += ",children: [";
            for (int i = 0; i < children.size(); i++) {
                result += ((Node) children.get(i)).toString() + ",";
            }
            result = result.substring(0, result.length() - 1);
            result += "]";
        } else {
            result += ",leaf:true";
        }
        return result + "}";
    }

    //兄弟节点横向排序
    public void sortChildren() {
        if (children.size() != 0) {
            Collections.sort(children, new NodeIDComparator());
            for (int i = 0; i < children.size(); i++) {
                ((Node) children.get(i)).sortChildren();
            }
        }
    }

    class NodeIDComparator implements Comparator {

        @Override
        public int compare(Object o1, Object o2) {
            int j1 = Integer.parseInt(((Node) o1).id);
            int j2 = Integer.parseInt(((Node) o2).id);
            return (j1 < j2 ? -1 : (j1 == j2 ? 0 : 1));
        }
    }
}

class VirtualDataGenerator {
    // 构造无序的结果集列表，实际应用中，该数据应该从数据库中查询获得；
    public static List getVirtualResult() {
        List dataList = new ArrayList();
        HashMap dataRecord1 = new HashMap();
        dataRecord1.put("id", "112000");
        dataRecord1.put("text", "廊坊银行解放道支行");
        dataRecord1.put("parentId", "110000");

        HashMap dataRecord2 = new HashMap();
        dataRecord2.put("id", "112200");
        dataRecord2.put("text", "廊坊银行三大街支行");
        dataRecord2.put("parentId", "112000");

        HashMap dataRecord3 = new HashMap();
        dataRecord3.put("id", "112100");
        dataRecord3.put("text", "廊坊银行广阳道支行");
        dataRecord3.put("parentId", "112000");

        HashMap dataRecord4 = new HashMap();
        dataRecord4.put("id", "113000");
        dataRecord4.put("text", "廊坊银行开发区支行");
        dataRecord4.put("parentId", "110000");

        HashMap dataRecord5 = new HashMap();
        dataRecord5.put("id", "100000");
        dataRecord5.put("text", "廊坊银行总行");
        dataRecord5.put("parentId", "");

        HashMap dataRecord6 = new HashMap();
        dataRecord6.put("id", "110000");
        dataRecord6.put("text", "廊坊分行");
        dataRecord6.put("parentId", "100000");

        HashMap dataRecord7 = new HashMap();
        dataRecord7.put("id", "111000");
        dataRecord7.put("text", "廊坊银行金光道支行");
        dataRecord7.put("parentId", "110000");

        dataList.add(dataRecord1);
        dataList.add(dataRecord2);
        dataList.add(dataRecord3);
        dataList.add(dataRecord4);
        dataList.add(dataRecord5);
        dataList.add(dataRecord6);
        dataList.add(dataRecord7);

        return dataList;
    }
}
