package common;

/**
 * Description:接口返回码和返回值 结合返回数据封装类ResponseWrapper，统一接口的数据返回格式
 *
 * @author: yeweilin@supcon.com
 * @date: 2019年01月10日 09:54
 */
public enum ReturnCode {

    SUCCESS("0000", "操作成功"),

    NODATA("0001", "操作成功无记录"),

    FEAILED("0002", "操作失败"),

    PARAMS_ERROR("1004", "参数为空或格式错误"),

    EXIST("1005", "已存在");

    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    ReturnCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
