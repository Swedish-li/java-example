package com.lrs.common.mock;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MyClass {

    @NonNull
    @Getter
    private Integer uniqueId;

    public void test(int nu) {
        System.out.println(nu);
    }
}
