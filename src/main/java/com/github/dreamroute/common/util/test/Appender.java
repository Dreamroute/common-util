package com.github.dreamroute.common.util.test;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.slf4j.LoggerFactory;

/**
 * <p>描述：抓取logbak打印的控制台日志信息，使用方式见测试用例：
 * <p><a href="https://stackoverflow.com/questions/1827677/how-to-do-a-junit-assert-on-a-message-in-a-logger/50268580">参考地址</a>
 *
 * @author w.dehi
 */
public class Appender {

    ListAppender<ILoggingEvent> apd = new ListAppender<>();

    public Appender(Class<?> cls) {
        Logger logger = (Logger) LoggerFactory.getLogger(cls);
        apd.start();
        logger.addAppender(apd);
    }

    public boolean contains(int index, String str) {
        return apd.list.get(index).getFormattedMessage().contains(str);
    }

    public boolean contains(String str) {
        return contains(0, str);
    }

}
