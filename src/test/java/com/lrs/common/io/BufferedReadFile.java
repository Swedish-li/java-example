package com.lrs.common.io;

import org.junit.Test;

import java.io.*;

public class BufferedReadFile {
    static String readFile(String fileName) throws IOException {
        try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {
            StringBuilder builder = new StringBuilder();
            String str;
            while ((str = in.readLine()) != null) {
                builder.append(str).append("\n");
            }
            return builder.toString();
        }
    }

    static String getClassPath() {
        return BufferedReadFile.class.getResource("/").getPath();
    }

    @Test
    public void testReadFile() throws IOException {
        System.out.println(readFile(getClassPath() + "table.sql"));
    }

    @Test
    public void testMemoryInput() throws IOException {

        try (StringReader stringReader = new StringReader(readFile(getClassPath() + "template/test.ftl"))) {
            int c;
            while ((c = stringReader.read()) != -1) {
                System.out.print((char) c);
            }
        }

    }

    @Test
    public void testDataInput() throws IOException {
        // 捕获EOF异常
        try (DataInputStream in = new DataInputStream(
                new ByteArrayInputStream(
                        readFile(getClassPath() + "message_zh_CN.properties").getBytes()))
        ) {
            while (true) {
                System.out.print((char) in.readByte());
            }
        } catch (EOFException err) {
            System.out.println("End of Stream");
        }

        try (DataInputStream in1 = new DataInputStream(
                new BufferedInputStream(
                        new FileInputStream(getClassPath() + "template/test.ftl")
                )
        )) {
            while (in1.available() != 0) {
                // 以字节为单位，会产生乱码
                System.out.print((char) in1.readByte());
            }
        }
    }
}
