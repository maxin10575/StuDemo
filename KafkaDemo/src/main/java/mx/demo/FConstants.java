package mx.demo;

public interface FConstants {

    class AFTERVEHICLEINFO {


    }

    class REDISINFO {

        //车辆OD
        public static final String KEY_AFTERVEHICLEOD = "AFTERVEHICLEOD";
        //车辆道路OD
        public static final String KEY_AFTERVEHICLEOD_ROAD = "AFTERVEHICLEOD_ROAD";
        //没有找到经纬度的卡口
        public static final String KEY_CHANNELCODEWITHOUTLNGANDLAT = "CHANNELCODEWITHOUTLNGANDLAT";


        //当天本地(最新)
        public static final String KEY_LOCALITY_DAY_NEW = "LOCALITY_DAY_NEW";
        //当天义乌车辆(最新)
        public static final String KEY_YIWUCAR_DAY_NEW = "YIWUCAR_DAY_NEW";
        //当天外地(最新)
        public static final String KEY_NONLOCAL_DAY_NEW = "NONLOCAL_DAY_NEW";
        //已统计的车辆
        public static final String KEY_COUNTCAR_DAY = "COUNTCAR";
        //当天保有量
        public static final String KEY_INVENTORY_DAY = "INVENTORY_DAY";


        //点位id和区域id对应的缓存的key
        public static final String KEY_DWLINKAREAYw = "DWLINKAREAYw";
        public static final String KEY_DWLINKMAREAYw = "DWLINKMAREAYw";
        public static final String KEY_DWLINKSAREAYw = "DWLINKSAREAYw";
        //es的索引
        public static final String KEY_ESINDEX = "ESINDEX";
        //点位经纬度
        public static final String LNGANDLAT = "LNGANDLAT";
        //按yiwuid的经纬度
        public static final String LNGANDLAT_BY_YIWUID = "LNGANDLATBYYIWUID";
        //金华车辆
        public static final String KEY_JINHUACARS = "JINHUACARS";
        //特殊种类卡口
        public static final String KEY_SPECIALKAKOU = "SPECIALKAKOU";
        //车辆库
        public static final String VEHICLELIST = "VEHICLELIST";

        //redis缓存新增文件夹："areaIdCount:"，放areaId的数据
        public static final String AREAID_COUNT = "areaIdCount:";
        //redis缓存新增文件夹recognize
        public static final String RECOGNIZE_COUNT = "recognizeCount:";
        //出行量
        public static final String KEY_TRIPS = "TRIPS";

        //治安点位信息
        public static final String KEY_ZHIANINFO = "ZHIANINFO";

    }

    class STORMIN {

        public final static String SPOUT = "SPOUT";
        public final static String MYSQL_INSERT_BOLT = "MYSQL_INSERT_BOLT";
        public final static String AnalyzeCountBolt = "AnalyzeCountBolt";
        public final static String ES_INSERT_BOLT = "ES_INSERT_BOLT";
        public final static String FIELD = "name";
        public final static String[] FIELDS = {"name", "age"};
        public final static String AnalyzeODBolt = "AnalyzeODBolt";
        public final static String AreaODBolt = "AreaODBolt";

        public final static String TotumengBolt = "TotumengBolt";

    }

    class ESBAYONETINFO {
        public static final String ES_INDEX = "bayonet_";
        public static final String ES_TYPE = "aftervehicle";


        public final static String ES_MAPPING = "{\n" +
                "  \"properties\": {\n" +
                "    \"bizExtCode\": {\n" +
                "      \"type\": \"keyword\",\n" +
                "      \"index\":true\n" +
                "    },\n" +
                "    \"plateNum\": {\n" +
                "      \"type\": \"keyword\",\n" +
                "      \"index\":true\n" +
                "    },\n" +
                "    \"carNumType\": {\n" +
                "      \"type\": \"keyword\",\n" +
                "      \"index\":true\n" +
                "    },\n" +
                "    \"carNumTypeStr\": {\n" +
                "      \"type\": \"keyword\",\n" +
                "      \"index\":true\n" +
                "    },\n" +
                "    \"capTime\": {\n" +
                "      \"type\": \"long\"\n" +
                "    },\n" +
                "    \"capTimeStr\": {\n" +
                "      \"type\": \"keyword\",\n" +
                "      \"index\":true\n" +
                "    },\n" +
                "    \"channelCode\": {\n" +
                "      \"type\": \"keyword\",\n" +
                "      \"index\":true\n" +
                "    },\n" +
                "    \"bizExtCode\": {\n" +
                "      \"type\": \"keyword\",\n" +
                "      \"index\":true\n" +
                "    },\n" +
                "    \"channelName\": {\n" +
                "      \"type\": \"keyword\",\n" +
                "      \"index\":true\n" +
                "    },\n" +
                "    \"orgName\": {\n" +
                "      \"type\": \"keyword\",\n" +
                "      \"index\":true\n" +
                "    },\n" +
                "    \"createTime\": {\n" +
                "      \"type\": \"long\"\n" +
                "    },\n" +
                "    \"createTimeStr\": {\n" +
                "      \"type\": \"keyword\",\n" +
                "      \"index\":true\n" +
                "    },\n" +
                "    \"carWayCode\": {\n" +
                "      \"type\": \"long\"\n" +
                "    },\n" +
                "    \"carWayCodeStr\": {\n" +
                "      \"type\": \"keyword\",\n" +
                "      \"index\":true\n" +
                "    },\n" +
                "    \"carSpeed\": {\n" +
                "      \"type\": \"double\"\n" +
                "    },\n" +
                "    \"carColor\": {\n" +
                "      \"type\": \"long\"\n" +
                "    },\n" +
                "    \"carColorStr\": {\n" +
                "      \"type\": \"keyword\",\n" +
                "      \"index\":true\n" +
                "    },\n" +
                "    \"plateNumUrl\": {\n" +
                "      \"type\": \"keyword\",\n" +
                "      \"index\":true\n" +
                "    },\n" +
                "    \"carImgUrl\": {\n" +
                "      \"type\": \"keyword\",\n" +
                "      \"index\":true\n" +
                "    },\n" +
                "    \"carBrand\": {\n" +
                "      \"type\": \"keyword\",\n" +
                "      \"index\":true\n" +
                "    },\n" +
                "    \"carBrandStr\": {\n" +
                "      \"type\": \"keyword\",\n" +
                "      \"index\":true\n" +
                "    },\n" +
                "    \"carDirect\": {\n" +
                "      \"type\": \"long\"\n" +
                "    },\n" +
                "    \"carDirectStr\": {\n" +
                "      \"type\": \"keyword\",\n" +
                "      \"index\":true\n" +
                "    },\n" +
                "    \"channelId\": {\n" +
                "      \"type\": \"keyword\",\n" +
                "      \"index\":true\n" +
                "    },\n" +
                "\n" +
                "\n" +
                "    \"siteCode\": {\n" +
                "      \"type\": \"keyword\",\n" +
                "      \"index\":true\n" +
                "    },\n" +
                "    \"lng\": {\n" +
                "      \"type\": \"double\"\n" +
                "    },\n" +
                "    \"lat\": {\n" +
                "      \"type\": \"double\"\n" +
                "    }\n" +
                "  }\n" +
                "}";


    }


}



