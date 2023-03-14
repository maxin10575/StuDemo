package test;

/**
 * @description:
 * @author: maxin
 * @create: 2023-02-11 10:20
 **/
public class HexAddTest {
    public static void main(String[] args) {

        System.out.println("观影: "+makeChecksum("6E01002E "));
        System.out.println("哺乳："+makeChecksum("6E01002F"));

        System.out.println("按摩器强度减弱："+makeChecksum("6E010035"));
        System.out.println("按摩器强度增强："+makeChecksum("6E010034"));

        System.out.println("背部腿部上升："+makeChecksum("6E010029"));
        System.out.println("背部腿部下降："+makeChecksum("6E01002A"));

        System.out.println("阅读 + 按摩器头+脚(闹钟)："+makeChecksum("6E010077"));
        System.out.println("阅读 + 按摩器头(闹钟)："+makeChecksum("6E01007A"));
        System.out.println("阅读 + 按摩器脚(闹钟）："+makeChecksum("6E010077D"));

        System.out.println("零重力 + 按摩器头+脚(闹钟):  "+makeChecksum("6E0100779"));
        System.out.println("零重力 + 按摩器头(闹钟):  "+makeChecksum("6E010077C"));
        System.out.println("零重力 + 按摩器脚(闹钟):  "+makeChecksum("6E010077F"));

        System.out.println("M1/观影 + 按摩器头 + 按摩器脚:  "+makeChecksum("6E0100781"));
        System.out.println("M1/观影 + 按摩器头:  "+makeChecksum("6E0100782"));
        System.out.println("M1/观影 + 按摩器脚:  "+makeChecksum("6E0100783"));

        System.out.println("M2/哺乳 + 按摩器头 + 按摩器脚(闹钟):  "+makeChecksum("6E01007A6"));
        System.out.println("M2/哺乳 + 按摩器头(闹钟):  "+makeChecksum("6E01007A7"));
        System.out.println("M2/哺乳 + 按摩器脚(闹钟):  "+makeChecksum("6E01007A8"));

        System.out.println("M3/止鼾 + 按摩器头 + 按摩器脚(闹钟):  "+makeChecksum("6E01007A9"));
        System.out.println("M3/止鼾 + 按摩器头(闹钟):  "+makeChecksum("6E01007AA"));
        System.out.println("M3/止鼾 + 按摩器脚(闹钟):  "+makeChecksum("6E01007AB"));

        System.out.println("瑜伽 + 按摩器头 + 按摩器脚(闹钟):  "+makeChecksum("6E01007AC"));
        System.out.println("瑜伽 + 按摩器头(闹钟):  "+makeChecksum("6E01007AD"));
        System.out.println("瑜伽 + 按摩器脚(闹钟):  "+makeChecksum("6E01007AE"));
    }

    public static String makeChecksum(String hexdata) {
        if (hexdata == null || hexdata.equals("")) {
            return "00";
        }
        hexdata = hexdata.replaceAll(" ", "");
        int total = 0;
        int len = hexdata.length();
        if (len % 2 != 0) {
            return "00";
        }
        int num = 0;
        while (num < len) {
            String s = hexdata.substring(num, num + 2);
            total += Integer.parseInt(s, 16);
            num = num + 2;
        }
        return  hexdata + Integer.toHexString(total);
    }

}
