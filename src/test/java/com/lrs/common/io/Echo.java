package com.lrs.common.io;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * stdin
 */
public class Echo {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        System.in
                )
        )) {
            String str;
            while ((str = reader.readLine()) != null && StringUtils.isNotBlank(str)) {
                System.out.println(str);
            }

        }
    }
}
