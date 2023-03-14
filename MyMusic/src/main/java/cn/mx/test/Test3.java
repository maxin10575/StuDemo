package cn.mx.test;

import cn.mx.bo.NoteBO;
import cn.mx.util.GenerateXmlUtil;
import cn.mx.util.NoteUtil;
import cn.mx.util.ReadXmlAsNoteUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gulihua
 * @Description
 * @date 2022-12-29 19:02
 */
public class Test3 {
    public static void main(String[] args) {
        List<NoteBO> noteListAcc1 = ReadXmlAsNoteUtil.getInstance()
//                .readAsNote("/Users/gulihua/Downloads/枫-周杰伦-伴奏1.xml", NoteBO.Mode.ACCOMPANIMENTS);
                .readAsNote("/Users/maxin/Documents/GitHub/StuDemo/MyMusic/src/main/resources/notes/大鱼_主奏.xml", NoteBO.Mode.ACCOMPANIMENTS);

        List<NoteBO> noteListAcc2 = ReadXmlAsNoteUtil.getInstance()
                .readAsNote("/Users/maxin/Documents/GitHub/StuDemo/MyMusic/src/main/resources/notes/大鱼_伴奏.xml", NoteBO.Mode.ACCOMPANIMENTS);
//                .readAsNote("/Users/gulihua/Downloads/枫-周杰伦-伴奏2.xml", NoteBO.Mode.ACCOMPANIMENTS);
        List<NoteBO> list = new ArrayList<>();
        list.addAll(noteListAcc1);
        list.addAll(noteListAcc2);

        list = NoteUtil.processMergeNoteBo(list);
        GenerateXmlUtil.getInstance().generateNote(list, "/Users/maxin/Documents/GitHub/StuDemo/MyMusic/src/main/resources/notes/大鱼_伴奏11.xml", "test");
//        GenerateXmlUtil.getInstance().generateNote(list, "/Users/gulihua/Downloads/枫-周杰伦-伴奏.xml", "test");

    }
}
