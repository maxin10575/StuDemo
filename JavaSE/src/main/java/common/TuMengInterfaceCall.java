package common;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author 操陈安
 * 图盟接口公共方法
 * @Date 2019/8/13 10:35
 */
public class TuMengInterfaceCall {

    //@Value("${tumeng.host}")
    private static  String host="33.185.134.140";
//    private static String host;

    //@Value("${tumeng.port}")
    private static String port="9001";
//    private static String port;

    private static String url="http://"+host+":"+port+"/device";

    private static String addUrl="http://"+host+":"+port+"/device/add";
    private static String querryUrl="http://"+host+":"+port+"/device/query";
    private static String delUrl="http://"+host+":"+port+"/device/delete";
    private static String updateUrl="http://"+host+":"+port+"/device/update";

    /**
     * 查询图盟接口数据
     * @param type 数据类型
     * @return 接口返回的数据（字符串类型）
     */
    public static String query(String type){
        Map<String,String> param= new HashMap<>();
        param.put("type",type);
        return HttpClientUtils.get(querryUrl,param);
    }

    /**
     * 新增数据接口
     * @param object 数据实体类
     * @param type 数据类型
     * @return 接口返回的数据（字符串类型）
     */
    public static String add(Object object,String type,String img){
        Map<String,String> param=new HashMap<>();
        param.put("type",type);
        param.put("content",JSONObject.toJSONString(object));
        param.put("img",img);
        JSONObject jsonObject= (JSONObject) JSONObject.parse(HttpClientUtils.post(addUrl,param));
        if(jsonObject.getInteger("status")==200 && "success".equals(jsonObject.getString("msg"))){
            return ((JSONObject)JSONObject.parse(jsonObject.getString("data"))).getString("uuid");
        }else {
            return null;
        }
    }

    /**
     * 修改数据接口
     * @param object 数据实体类
     * @param type 数据类型
     * @param uuid 唯一字段
     * @return true 新增成功、false 新增失败
     */
    public static boolean update(Object object,String type,String img,String uuid){
        Map<String,String> param= new HashMap<>();
        param.put("type",type);
        param.put("uuid",uuid);
        param.put("content",JSONObject.toJSONString(object));
        param.put("img",img);
        JSONObject jsonObject= (JSONObject) JSONObject.parse(HttpClientUtils.post(updateUrl,param));
        return jsonObject.getInteger("status")==200 && "success".equals(jsonObject.getString("msg"));
    }

    /**
     * 删除数据接口
     * @param type 数据类型
     * @param uuid 唯一字段
     * @return
     */
    public static boolean delete(String type,String uuid){
        Map<String,String> param= new HashMap<>();
        param.put("type",type);
        param.put("uuid",uuid);
        JSONObject jsonObject= (JSONObject) JSONObject.parse(HttpClientUtils.get(delUrl,param));
        return jsonObject.getInteger("status")==200 && "success".equals(jsonObject.getString("msg"));
    }

    /*public static void main(String[] args) {
        TfamsDevJasbSjz of=new TfamsDevJasbSjz();
        of.setLat("29.0");
        of.setLng("120.0");
        of.setSslx("1243");
        String str=TuMengInterfaceCall.add(of,"示警桩");
        System.out.println(str);
    }*/

}
