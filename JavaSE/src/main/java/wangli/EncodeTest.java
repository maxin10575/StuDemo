/*
package wangli;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

*/
/**
 * @description:
 * @author: maxin
 * @create: 2022-08-11 19:18
 **//*

public class EncodeTest {
    public static String Encode_QR_CODE() throws IOException, WriterException {
//        String contents = "王师百万拥旌旗\n" +
//                "俊彦登坛属少公\n" +
//                "帅阃分茅新建节\n" +
//                "哥舒射虎出雕弓";
        String contents = "大成是沙雕!";
        // 二维码内容
        int width = 430; // 二维码图片宽度 300
        int height = 430; // 二维码图片高度300

        String format = "png";// 二维码的图片格式 gif

        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        // 指定纠错等级,纠错级别（L 7%、M 15%、Q 25%、H 30%）
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        // 内容所使用字符集编码
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
//		hints.put(EncodeHintType.MAX_SIZE, 350);//设置图片的最大值
//	    hints.put(EncodeHintType.MIN_SIZE, 100);//设置图片的最小值
        hints.put(EncodeHintType.MARGIN, 1);//设置二维码边的空度，非负数

        BitMatrix bitMatrix = new MultiFormatWriter().encode(contents,//要编码的内容
                //编码类型，目前zxing支持：Aztec 2D,CODABAR 1D format,Code 39 1D,Code 93 1D ,Code 128 1D,
                //Data Matrix 2D , EAN-8 1D,EAN-13 1D,ITF (Interleaved Two of Five) 1D,
                //MaxiCode 2D barcode,PDF417,QR Code 2D,RSS 14,RSS EXPANDED,UPC-A 1D,UPC-E 1D,UPC/EAN extension,UPC_EAN_EXTENSION
                BarcodeFormat.QR_CODE,
                width, //条形码的宽度
                height, //条形码的高度
                hints);//生成条形码时的一些配置,此项可选

        // 生成二维码
        File outputFile = new File("/Users/maxin/Desktop" + File.separator + "new.png");//指定输出路径
        MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);

        //
       */
/* BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
        ByteArrayOutputStream os = new ByteArrayOutputStream();//新建流。
        ImageIO.write(image, format, os);//利用ImageIO类提供的write方法，将bi以png图片的数据模式写入流。
        byte b[] = os.toByteArray();//从流中获取数据数组。
        String str = new BASE64Encoder().encode(b);
        IOUtils.closeQuietly(os);*//*

        return null;
    }

    public static void main(String[] args) throws Exception {
        try {
            System.out.println( Encode_QR_CODE());
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }


}
*/
