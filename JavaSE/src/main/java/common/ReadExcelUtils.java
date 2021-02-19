package common;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author:cca
 * @create: 2019-01-28 13:55
 */
public class ReadExcelUtils {
    //sheetIndex：读sheet几表，rowIndex：第几行开始读
    public List<List<String>> readExcelBySheetIndex(InputStream file, int sheetIndex, int rowIndex) {
        List<List<String>> list = new ArrayList<List<String>>();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

        //File excelFile = file; // 创建文件对象
        try {
            // 文件流
            //FileInputStream is = new FileInputStream(file);
            // 支持Excel 2003 2007
            Workbook workbook = WorkbookFactory.create(file);
            int sheetCount = workbook.getNumberOfSheets(); // Sheet的数量
            int cellCount = 0;
            // 遍历每个Sheet
            Sheet sheet = workbook.getSheetAt(sheetIndex);
            int rowCount = sheet.getPhysicalNumberOfRows(); // 获取总行数
            // 遍历每一行
            for (int r = rowIndex; r < rowCount; r++) {
                Row row = sheet.getRow(r);
                if (row == null) {// 若为null，表明中间有一行为空值，但是获得总行数是没有获得这一行，所以rowCount+1
                    rowCount++;
                    continue;
                }
                if (cellCount == 0){
                    cellCount = row.getPhysicalNumberOfCells(); // 获取总列数
                }

                List<String> rows = new ArrayList<String>();
                // 遍历每一列
                for (int c = 0; c < cellCount; c++) {
                    Cell cell = row.getCell(c);
                    if (cell == null) {
                        rows.add("");
                        continue;
                    }
                    int cellType = cell.getCellType();
                    String cellValue = null;
                    switch (cellType) {
                        case Cell.CELL_TYPE_STRING: // 文本
                            cellValue = cell.getStringCellValue();
                            break;
                        case Cell.CELL_TYPE_NUMERIC: // 数字、日期
                            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                cellValue = fmt.format(cell.getDateCellValue()); // 日期型
                            } else {
                                cell.setCellType(HSSFCell.CELL_TYPE_STRING);// 将数字转成字符串
                                cellValue = cell.getStringCellValue();
                            }
                            break;
                        case Cell.CELL_TYPE_BOOLEAN: // 布尔型
                            cellValue = String.valueOf(cell
                                    .getBooleanCellValue());
                            break;
                        case Cell.CELL_TYPE_BLANK: // 空白
                            cellValue = cell.getStringCellValue();
                            break;
                        case Cell.CELL_TYPE_ERROR: // 错误
                            cellValue = String
                                    .valueOf(cell.getErrorCellValue());
                            break;
                        case Cell.CELL_TYPE_FORMULA: // 公式
                            // 用数字方式获取公式结果，根据值判断是否为日期类型
                            double numericValue = cell.getNumericCellValue();
                            if (HSSFDateUtil.isValidExcelDate(numericValue)) { // 如果是日期类型
                                cellValue = fmt.format(cell.getDateCellValue());
                            } else {
                                cellValue = String.valueOf(numericValue);
                            }
                            break;
                        default:
                            cellValue = "";
                    }
                    rows.add(cellValue);
                }
                list.add(rows);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }
}
