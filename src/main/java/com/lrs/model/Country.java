package com.lrs.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "country")
// get.set,toString,equal,canEqual,hashCode
@Data
// 链式调用
@Accessors(chain = true)
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String countryname;

    private String countrycode;

}