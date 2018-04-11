package com.lrs.model;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 传统的 Java Bean 模式
 * <p>
 * 1、类在构造过程中可能处于不一致的状态（inconsistent state）
 * <p>
 * 2、阻止了类成为不可变类的可能性
 *
 * @author Swedish-li
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class Brand implements Serializable {

    private static final long serialVersionUID = -2236741466672571896L;
    @NonNull
    private Long id;

    private String name;

    private String description;

    private String imgUrl;

    private Integer sort;

    private Integer isDisplay;

}
