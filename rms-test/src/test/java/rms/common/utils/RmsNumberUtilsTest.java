package rms.common.utils;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class RmsNumberUtilsTest {

    @Test
    public final void test_isInteger() {
        assertThat(RmsNumberUtils.isInteger("1"), is(true));
        assertThat(RmsNumberUtils.isInteger("1.1"), is(false));
        assertThat(RmsNumberUtils.isInteger("0.1"), is(false));
        assertThat(RmsNumberUtils.isInteger("x"), is(false));
    }

}
