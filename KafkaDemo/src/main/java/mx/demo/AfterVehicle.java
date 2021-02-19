package mx.demo;


import lombok.Data;

/*@Document(indexName = FConstants.ESBAYONETINFO.ES_INDEX , type =  FConstants.ESBAYONETINFO.ES_TYPE)
@Mapping(mappingPath = "bayonet.json") // 解决IK分词不能使用问题*/
@Data
public class AfterVehicle {

       private String plateNum;
       private String carNumType;
       private String carNumTypeStr;
       private long capTime;
       private String capTimeStr;
       private String channelCode;
       private String channelName;
       private String bizExtCode;
       private String orgName;
       private long createTime;
       private String createTimeStr;
       private int carWayCode;
       private String carWayCodeStr;
       private double carSpeed;
       private int carColor;
       private String carColorStr;
       private String plateNumUrl;
       private String carImgUrl;
       private String carBrand;
       private String carBrandStr;
       private int carDirect;
       private String arDirectStr;
       private String channelId;
       private double lng;
       private double lat;
       private String siteCode;

       //1:卡口，2:停车场
       private int bayonetType;
}


/*
       recordId : 类型 string 。过车记录索引号。
       plateNum : 类型 string 。车牌号码。
       carNumType : 类型 string 。号牌类型。    数据字典如下
       carNumTypeStr : 类型 string 。号牌类型。
       capTime : 类型 long 。过车时间的毫秒时间戳。
       capTimeStr : 类型 string 。过车时间，格式 "YYYY-MM-DD HH:mm:ss" 。
       channelCode : 类型 string 。过车地点。
       channelName : 类型 string 。过车地点名称。
       orgName : 类型 string 。组织名称。
       createTimeStr : 类型 string 。入库时间，格式 "YYYY-MM-DD HH:mm:ss" 。
       carWayCode : 类型 int 。车道编号。		数据字典如下
       carWayCodeStr : 类型 string 。车道编号翻译。
       carSpeed : 类型 int 。车辆速度。
       carColor : 类型 int 。车身颜色。			数据字典如下
       carColorStr : 类型 string 。车身颜色。
       plateNumUrl : 类型 string 。车牌小图片。
       carImgUrl : 类型 string 。过车图片。
       carBrand : 类型 string 。车辆品牌编码。	没有数据字典
       carBrandStr : 类型 string 。车辆品牌名称。
       carDirect : 类型 int 。行驶方向。		数据字典如下
       carDirectStr : 类型 string 。行车方向。
       channelId : 类型 string 。通道编号。
       lng : 类型 string 。经度。
       lat : 类型 string 。纬度。
       */

