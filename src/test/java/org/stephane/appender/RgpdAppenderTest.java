package org.stephane.appender;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class RgpdAppenderTest {

    @Test
    void newInstance() {
        RgpdAppender rgpdAppender = RgpdAppender.newInstance();
        Assertions.assertThat(rgpdAppender).isNotNull();
    }

}
