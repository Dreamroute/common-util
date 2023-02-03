package com.github.dreamroute.common.util.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
class AppenderTest {

    @Test
    void baseTest() {
        Appender appender = new Appender(AppenderTest.class);
        String str = "测试";
        log.info(str);
        assertTrue(appender.contains(str));
    }

}
