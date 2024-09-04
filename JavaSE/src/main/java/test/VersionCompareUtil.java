package test;

/**
 * 版本比较工具类
 *
 * @author chenzhen
 * @date 2022/3/8
 */
public class VersionCompareUtil {


    public static void main(String[] args) {
        String start = "V1.1.7";
        String end = "2.1.8";
       System.out.println(checkLeftBig(start,end));
    }

    /**
     * 比较版本大小
     * <p>
     * 说明：支n位基础版本号+1位子版本号
     * 示例：1.0.2>1.0.1 , 1.0.1.1>1.0.1
     *
     * @param version1 版本1
     * @param version2 版本2
     * @return 0:相同 1:version1大于version2 -1:version1小于version2
     */
    public static int compareVersion(String version1, String version2) {
        if (version1.equals(version2)) {
            //版本相同
            return 0;
        }

        final String vString = "v";
        final String VString = "V";
        final String xString = "_";
        final String dString = ".";

        if (version1.contains(vString)) {
            version1 = version1.substring(version1.indexOf(vString) + 1);
        }
        if (version2.contains(vString)) {
            version2 = version2.substring(version2.indexOf(vString) + 1);
        }
        if (version1.contains(VString)) {
            version1 = version1.substring(version1.indexOf(VString) + 1);
        }
        if (version2.contains(VString)) {
            version2 = version2.substring(version2.indexOf(VString) + 1);
        }

        if (version1.contains(xString)) {
            version1 = version1.replace(xString, dString);
        }
        if (version2.contains(xString)) {
            version2 = version2.replace(xString, dString);
        }

        String[] v1Array = version1.split("\\.");
        String[] v2Array = version2.split("\\.");
        int v1Len = v1Array.length;
        int v2Len = v2Array.length;
        //基础版本号位数（取长度小的）
        int baseLen = 0;
        if (v1Len > v2Len) {
            baseLen = v2Len;
        } else {
            baseLen = v1Len;
        }

        //基础版本号比较
        for (int i = 0; i < baseLen; i++) {
            //同位版本号相同
            if (v1Array[i].equals(v2Array[i])) {
                //比较下一位
                continue;
            } else {
                return Integer.parseInt(v1Array[i]) > Integer.parseInt(v2Array[i]) ? 1 : -1;
            }
        }
        //基础版本相同，再比较子版本号
        if (v1Len != v2Len) {
            return v1Len > v2Len ? 1 : -1;
        } else {
            //基础版本相同，无子版本号
            return 0;
        }
    }

    /**
     * 检查左边版本大于右边
     *
     * @param leftVer
     * @param rightVer
     * @return
     */
    public static boolean checkLeftBig(String leftVer, String rightVer) {

        final int verNum = VersionCompareUtil.compareVersion(leftVer, rightVer);
        if (verNum == 1) {
            return true;
        }
        return false;
    }

    /**
     * 检查两个版本相等
     *
     * @param leftVer
     * @param rightVer
     * @return
     */
    public static boolean checkEqual(String leftVer, String rightVer) {

        final int verNum = VersionCompareUtil.compareVersion(leftVer, rightVer);
        if (verNum == 0) {
            return true;
        }
        return false;
    }

}