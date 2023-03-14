package cn.mx.util;

import cn.hutool.core.io.FileUtil;
import cn.mx.bo.NoteBO;
import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * @author gulihua
 * @Description
 * @date 2022-11-30 23:28
 */
public class ReadJsonAsNoteUtil
{
    private static ReadJsonAsNoteUtil instance;

    private ReadJsonAsNoteUtil()
    {
    }


    public static ReadJsonAsNoteUtil getInstance()
    {
        synchronized (ReadJsonAsNoteUtil.class)
        {
            if (instance == null)
            {
                instance = new ReadJsonAsNoteUtil();
            }
        }

        return instance;
    }


    /**
     * 读取 JSON 文件
     *
     * @param filePath 文件全路径
     * @date 22:32 2022/11/30
     * @author gulihua
     *
     * @return
     * @throws
     **/
    public List<NoteBO> readAsNote(String filePath)
    {
        return JSON.parseArray(FileUtil.readUtf8String(filePath), NoteBO.class);

    }
}
