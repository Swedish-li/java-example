package com.lrs.common.jackson;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class Model {

    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal decimal;

    private Map<String, String> map;

    @JsonProperty("date_now")
    @JsonSerialize(using = DateSerializer.class)
    private Date date;

    private List<String> list;

    private String name;
}
