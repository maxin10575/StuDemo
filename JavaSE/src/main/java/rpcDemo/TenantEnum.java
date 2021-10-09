package rpcDemo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import utils.StringUtils;

import java.util.Objects;

/**
 * @author songliangliang
 * @since 2021/9/24
 */
@Getter
public enum TenantEnum {

    PROMOTER(1, "promoter"), CUSTOMER(2, "customer"), API_SELLER(3, "api_seller"),DEFAULT(4,"default");

    /**
     * code
     */
    @Getter(onMethod_ = {@JsonValue})
    private final Integer code;

    /**
     * name
     */
    private final String name;

    TenantEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    @JsonCreator
    public static TenantEnum valueOf(Integer code) {
        if (Objects.isNull(code)) {
            return TenantEnum.DEFAULT;
        }
        for (TenantEnum v : values()) {
            if (v.getCode().equals(code)) {
                return v;
            }
        }
        return TenantEnum.DEFAULT;
    }

    public static TenantEnum nameOf(String name) {
        if (StringUtils.isBlank(name)) {
            return TenantEnum.DEFAULT;
        }
        for (TenantEnum v : values()) {
            if (v.getName().equals(name)) {
                return v;
            }
        }
        return TenantEnum.DEFAULT;
    }

}
