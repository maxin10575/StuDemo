package cn.mx.test;

import cn.mx.play.AppStart;

/**
 * @author maxin
 * @Description 可以使用--牛逼
 * @date 2023-2-28 19:02
 */
public class PlayStart {
    public static void main(String[] args) {

        AppStart.run(
                "/Users/maxin/Documents/GitHub/StuDemo/MyMusic/src/main/resources/notes/大鱼_主奏.xml",
                "/Users/maxin/Documents/GitHub/StuDemo/MyMusic/src/main/resources/notes//大鱼_伴奏.xml",
                "大鱼(周深)");
    }


//    public static void main(String[] args) {
//        List<NoteBO> noteListAcc1 = ReadXmlAsNoteUtil.getInstance()
//                .readAsNote("/Users/maxin/Documents/GitHub/StuDemo/MyMusic/src/main/resources/notes/大鱼_主奏.xml", NoteBO.Mode.ACCOMPANIMENTS);
//
//        List<NoteBO> noteListAcc2 = ReadXmlAsNoteUtil.getInstance()
//                .readAsNote("/Users/maxin/Documents/GitHub/StuDemo/MyMusic/src/main/resources/notes/大鱼_伴奏.xml", NoteBO.Mode.ACCOMPANIMENTS);
//        List<NoteBO> list = new ArrayList<>();
//        list.addAll(noteListAcc1);
//        list.addAll(noteListAcc2);
//
//        list = NoteUtil.processMergeNoteBo(list);
//        GenerateXmlUtil.getInstance().generateNote(list, "/Users/maxin/Documents/GitHub/StuDemo/MyMusic/src/main/resources/notes/大鱼_伴奏11.xml", "test");
//
//    }
}
