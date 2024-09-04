package easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

/**
 * @description:
 * @author: maxin
 * @create: 2023-03-24 16:51
 **/

@Data
@ContentRowHeight(20)
@HeadRowHeight(25)
@ColumnWidth(25)
public class ExportModel {
    @ExcelProperty(value = "姓名" ,index = 0)
    private String name;

    @ExcelProperty(value = "性别" ,index = 1)
    private String sex;

    @ExcelProperty(value = "年龄" ,index = 2)
    private Integer age;

}
