package com.lrs.logback;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * logback-classic
 * logback-core
 * slf4j-api
 * logback-access
 */
public class HelloWorld {
    /**
     * ロギングの基本的なテンプレート
     */
    @Test
    public void test1() {
        Logger logger = LoggerFactory.getLogger(HelloWorld.class);
        logger.debug("Hello World!");
    }

    @Test
    public void test2() {
        Logger logger = LoggerFactory.getLogger(HelloWorld.class);
        logger.debug("Hello World2!");

        // print internal state
        LoggerContext ctx = (LoggerContext) LoggerFactory.getILoggerFactory();
        StatusPrinter.print(ctx);
    }
}
