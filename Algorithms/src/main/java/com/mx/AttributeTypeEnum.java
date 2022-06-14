package com.mx;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @Description: 扩展属性
 * @Author: maxin
 * @Date: 2021/8/25
 */
@AllArgsConstructor
@Getter
public enum AttributeTypeEnum {

    QUICK_CREDIT(1,"快充"),
    LOW_CREDTI(2,"慢充");

    private Integer type;
    private String desc;



    public String getDesc() {
        return desc;
    }

    public static String getDescByType(Integer type) {
//        return Arrays.stream(values()).filter(value -> Objects.equals(type + "", value.getType())).map(AttributeTypeEnum::getDesc).findFirst().orElse("undefined");
        return Arrays.stream(values()).filter(value -> Objects.equals(type + "", value.getType().toString())).map(AttributeTypeEnum::getDesc).findFirst().orElse("undefined");

    }


    public static Integer getTypeByDesc(String desc) {
        Objects.requireNonNull(desc);
        return Arrays.stream(values()).filter(value -> Objects.equals(desc + "", value.getDesc())).map(AttributeTypeEnum::getType).findFirst().orElse(null);
       /* AttributeTypeEnum[] enums = AttributeTypeEnum.values();
        for (AttributeTypeEnum attributeTypeEnum : enums) {
            if (desc.equals(attributeTypeEnum.getDesc())) {
                return attributeTypeEnum.getType();
            }
        }
        return null;*/
    }

    public static AttributeTypeEnum getByType(Integer type) {
        Objects.requireNonNull(type);
        return Arrays.stream(values()).filter(value -> Objects.equals(type + "", value.getType().toString())).findFirst().orElse(null);
/*        AttributeTypeEnum[] enums = AttributeTypeEnum.values();
        for (AttributeTypeEnum phoneCreditTypeEnum : enums) {
            if (phoneCreditTypeEnum.getType().equals(type)) {
                return phoneCreditTypeEnum;
            }
        }
        return null;*/
    }

    public static List<AttributeTypeVo> getAllPhoneCreditType() {
        AttributeTypeEnum[] enums = AttributeTypeEnum.values();
        List<AttributeTypeVo> attributeTypeVos = new ArrayList();
        for (AttributeTypeEnum phoneCreditTypeEnum : enums) {
            AttributeTypeVo attributeTypeVo = new AttributeTypeVo();
            attributeTypeVo.setType(phoneCreditTypeEnum.getType());
            attributeTypeVo.setDesc(phoneCreditTypeEnum.getDesc());
            attributeTypeVos.add(attributeTypeVo);
        }
        return attributeTypeVos;
    }
}
