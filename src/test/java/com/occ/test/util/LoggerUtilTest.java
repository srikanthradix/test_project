package com.occ.test.util;

import org.apache.log4j.Appender;
import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;
import org.junit.After;
import org.junit.Before;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.apache.log4j.LogManager.getRootLogger;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by Srikanth Vuppula on 3/4/16.
 */
public class LoggerUtilTest {

    @Mock
    protected Appender mockAppender;
    @Captor
    protected ArgumentCaptor<LoggingEvent> captorLoggingEvent;

    protected void setUp() {
        getRootLogger().addAppender(mockAppender);
    }

    protected void tearDown() throws Exception {
        getRootLogger().removeAppender(mockAppender);
    }

    protected void verifyLoggedMessage(String message, Level level) {
        verify(mockAppender, times(1)).doAppend(captorLoggingEvent.capture());
        LoggingEvent loggingEvent = captorLoggingEvent.getValue();
        assertNotNull(loggingEvent);
        //Check the message being logged
        assertThat(loggingEvent.getRenderedMessage(), is(message));
        //Check log level
        assertThat(loggingEvent.getLevel(), is(level));
    }

}
