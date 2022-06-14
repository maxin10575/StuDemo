package com.mx;

import java.util.Arrays;
import java.util.Objects;

/**
 * @description:
 * @author: maxin
 * @create: 2021-11-18 20:33
 **/
public enum testEnmu {
        LOCAL(1),
        BONDED(2),
        DIRECT_MAILLING_BC(3),
        DIRECT_MAILLING_CC(4),
        CROSS_BORDER_BB(5);

        private int code;


        private testEnmu(int code) {
                this.code = code;
        }

        public int getCode() {
                return this.code;
        }

        public static String getDescByCode(Integer code) {
                Objects.requireNonNull(code);
                return Arrays.stream(values()).filter(value -> Objects.equals(code,value.getCode())).findFirst().orElse(null).toString();
        }


}
