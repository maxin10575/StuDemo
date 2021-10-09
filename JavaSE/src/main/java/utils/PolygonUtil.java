package utils;

import com.alibaba.fastjson.JSONArray;
import org.apache.commons.lang3.StringUtils;

import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 张国强
 * @date 2020/7/28 15:06
 */
public class PolygonUtil {

    /**
     * 判断经纬度是否在范围内
     * @param lnt 经度
     * @param lat 纬度
     * @param range 格式[[[x,y],[x,y]]]
     * @return
     */
   public static boolean isWithinRange(double lnt, double lat, String range) {
        Polygon polygon = new Polygon();
        boolean betweenArea = false;
        if(StringUtils.isNotEmpty(range)&&range.contains("[[[")) {
            String rangeArea = range.replaceAll("\\[\\[", "").replaceAll("\\]\\]", "");
            double[][] doubles = PolygonUtil.jsonToArray(rangeArea);
            polygon.setPoints(doubles);
            polygon.setNpoints(polygon.getPoints().length);
            betweenArea = polygon.contains(lnt, lat);
        }
        return betweenArea;
    }


    public static boolean checkWithJdkGeneralPath(Point2D.Double point, List<Point2D.Double> polygon) {
        GeneralPath p = new GeneralPath();
        Point2D.Double first = polygon.get(0);
        p.moveTo(first.x, first.y);
        for (Point2D.Double d : polygon) {
            p.lineTo(d.x, d.y);
        }
        p.lineTo(first.x, first.y);
        p.closePath();
        return p.contains(point);
    }

    public static double[][] jsonToArray(String json) {
        json = "[" + json + "]";
        JSONArray array = JSONArray.parseArray(json);
        double[][] result = new double[array.size()][2];
        int i = 0;
        BigDecimal bg;
        for (Object point : array) {
            JSONArray points = JSONArray.parseArray(String.valueOf(point));
            double[] _points = new double[2];
            bg = new BigDecimal(String.valueOf(points.get(0)));
            _points[0] = bg.doubleValue();
            bg = new BigDecimal(String.valueOf(points.get(1)));
            _points[1] = bg.doubleValue();
            result[i] = _points;
            i++;
        }
        return result;
    }

    public static Map<String, Double> getMaxMinXY(double[][] result) {
        /**
         * 获取经纬度最大最小值
         */
        Map<String, Double> map = new HashMap<>();
        double maxX = 0.0;
        double maxY = 0.0;
        double minX = 140.0;
        double minY = 140.0;
        for (double[] doubles : result) {
            if (doubles[0] > maxX) {
                maxX = doubles[0];
            } else if (doubles[0] < minX) {
                minX = doubles[0];
            }
            if (doubles[1] > maxY) {
                maxY = doubles[1];
            } else if (doubles[1] < minY) {
                minY = doubles[1];
            }
        }
        map.put("maxX", maxX);
        map.put("maxY", maxY);
        map.put("minX", minX);
        map.put("minY", minY);
        return map;
    }


}
