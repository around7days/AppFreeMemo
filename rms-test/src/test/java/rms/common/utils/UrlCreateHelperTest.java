package rms.common.utils;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import rms.SpringWebApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringWebApplication.class, properties = "spring.profiles.active=test")
public class UrlCreateHelperTest {

    @Autowired
    UrlCreateHelper helper;

    @Test
    public final void test_redirect_パラメータなし() {
        String url = "html/menu";
        String redirectUrl = helper.redirect(url);
        assertThat(redirectUrl, is("redirect:html/menu"));
    }

    @Test
    public final void test_redirect_パラメータあり() {
        String url = "html/menu";
        String methodNm = "aaa";
        String redirectUrl = helper.redirect(url, methodNm);
        assertThat(redirectUrl, is("redirect:html/menu?aaa"));
    }

    @Test
    public final void test_forward_パラメータなし() {
        String url = "html/menu";
        String redirectUrl = helper.forward(url);
        assertThat(redirectUrl, is("forward:html/menu"));
    }

    @Test
    public final void test_forward_パラメータあり() {
        String url = "html/menu";
        String methodNm = "aaa";
        String redirectUrl = helper.forward(url, methodNm);
        assertThat(redirectUrl, is("forward:html/menu?aaa"));
    }

}
