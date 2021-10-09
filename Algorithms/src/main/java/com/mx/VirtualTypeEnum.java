package com.mx;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/***
 * @Description: 虚拟商品类型
 * @Author: maxin
 * @Date: 2021/8/23
 */
@AllArgsConstructor
@Getter
public enum VirtualTypeEnum {

    PHONE_CREDIT(1, "话费充值");

    private Integer virtualGoodsType;

    private String virtualGoodsTypeDESC;


    public String getDesc() {
        return virtualGoodsTypeDESC;
    }

    public static String getDesc(Integer code) {
        return Arrays.stream(values()).filter(value -> Objects.equals(code + "", value.getVirtualGoodsType().toString())).map(VirtualTypeEnum::getDesc).findFirst().orElse("undefined");
    }

    public static Integer getByDesc(String desc) {
        Objects.requireNonNull(desc);
        VirtualTypeEnum[] enums = VirtualTypeEnum.values();
        for (VirtualTypeEnum virtualTypeEnum : enums) {
            if (desc.equals(virtualTypeEnum.getVirtualGoodsTypeDESC())) {
                return virtualTypeEnum.getVirtualGoodsType();
            }
        }
        return null;
    }

    public static VirtualTypeEnum getByType(Integer type) {
        Objects.requireNonNull(type);
        VirtualTypeEnum[] enums = VirtualTypeEnum.values();
        for (VirtualTypeEnum virtualTypeEnum : enums) {
            if (virtualTypeEnum.getVirtualGoodsType().equals(type)) {
                return virtualTypeEnum;
            }
        }
        return null;
    }

    public static List<VirtualTypeVo> getAllVirtualType() {
        VirtualTypeEnum[] enums = VirtualTypeEnum.values();
        List<VirtualTypeVo> tradeTypeVOS = new ArrayList();
        for (VirtualTypeEnum virtualTypeEnum : enums) {
            VirtualTypeVo tradeTypeVO = new VirtualTypeVo();
            tradeTypeVO.setVirtualGoodsType(virtualTypeEnum.getVirtualGoodsType());
            tradeTypeVO.setVirtualGoodsTypeDESC(virtualTypeEnum.getDesc());
            tradeTypeVOS.add(tradeTypeVO);
        }
        return tradeTypeVOS;
    }

}
