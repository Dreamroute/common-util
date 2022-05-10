package com.github.dreamroute.common.util;

import org.junit.jupiter.api.Test;

import static com.github.dreamroute.common.util.StrUtil.isEmpty;
import static com.github.dreamroute.common.util.StrUtil.isNotEmpty;
import static com.github.dreamroute.common.util.StrUtil.toCamelCase;
import static com.github.dreamroute.common.util.StrUtil.toUnderlineCase;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author w.dehi.2022-05-10
 */
class StrUtilTest {

    @Test
    void isEmptyTest() {
        assertTrue(isEmpty(null));
        assertTrue(isEmpty(""));
    }

    @Test
    void isNotEmptyTest() {
        assertFalse(isNotEmpty(null));
        assertFalse(isNotEmpty(""));
    }

    @Test
    void toUnderlineCaseTest() {
        assertNull(toUnderlineCase(null));
        assertEquals("", toUnderlineCase(""));
        assertEquals("user_name", toUnderlineCase("userName"));
    }

    @Test
    void toCamelCaseTest() {
        assertNull(null);
        assertEquals("", toCamelCase(""));
        assertEquals("userName", toCamelCase("user_name"));
    }

}
