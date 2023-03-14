package com.mx;

import lombok.Builder;
import lombok.Data;

/**
 * @program: StuDemo
 * @description:
 * @author:
 * @create: 2020-09-30 15:57
 * @Modified By:
 * @Version: 1.0
 **/
@Data
@Builder
public class User {
        String name;
        Integer age;
        Double index;
        String brithday;
}

