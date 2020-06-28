package com.mx.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class TestModel implements Serializable {

    private static final long serialVersionUID = 8540138242906397358L;
    private long id;
    private String type;
    /**
     * 这里是一个列表
     */
    private List<Integer> catIds;



}
