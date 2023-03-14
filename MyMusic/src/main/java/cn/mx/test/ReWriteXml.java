package cn.mx.test;

import cn.mx.bo.NoteBO;
import cn.mx.util.ReadXmlAsNoteUtil;

import java.util.List;

/**
 * @author gulihua
 * @Description
 * @date 2023-01-18 14:30
 */
public class ReWriteXml extends TestAudio{
    public static void main(String[] args) {
        List<NoteBO> noteListAcc = ReadXmlAsNoteUtil.getInstance()
                .readAsNote("/Users/gulihua/Downloads/枫-周杰伦-伴奏.xml", NoteBO.Mode.ACCOMPANIMENTS);

    }
}
