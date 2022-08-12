package org.stephane.appender;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomAppenderTest {

    @Test
    void newInstance() {
        CustomAppender  customAppender = CustomAppender.newInstance();
        Assertions.assertThat(customAppender).isNotNull();
    }

}
