package cn.mx.test;

import cn.mx.play.AppStart;

/**
 * @author maxin
 * @Description
 * @date 2023-2-28 19:02
 */
public class Test2 {
    public static void main(String[] args) {

        AppStart.run(
                "/Users/maxin/Documents/GitHub/StuDemo/MyMusic/src/main/resources/notes/大鱼_主奏.xml",
                "/Users/maxin/Documents/GitHub/StuDemo/MyMusic/src/main/resources/notes//大鱼_伴奏.xml",
                "大鱼(周深)");
    }
}
