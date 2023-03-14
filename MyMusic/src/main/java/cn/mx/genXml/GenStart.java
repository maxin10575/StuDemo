package cn.mx.genXml;

/**
 * 根据 mid 文件生成 xml 文件的运行类
 * !!重要：运行前请先看 Readme 中相应的方法
 * @author gulihua
 * @Description
 * @date 2023-02-10 16:29
 */
public class GenStart
{
    public static void main(String[] args) throws Exception
    {
        String midiPath = "/Users/maxin/Documents/GitHub/StuDemo/MyMusic/src/main/resources/notes/xqgM.mid";
        String xmlPath = "/Users/maxin/Documents/GitHub/StuDemo/MyMusic/src/main/resources/notes/xqgB.mid";
        MidParseAndGenXML gen = new MidParseAndGenXML(midiPath, xmlPath);
        gen.run();
    }
}
