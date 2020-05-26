package com.mx.distributid;

public interface FormNoGenerateService {

    /**
     * 根据单据编号类型 生成单据编号
     */
    String generateFormNo(FormNoTypeEnum formNoTypeEnum);
}
