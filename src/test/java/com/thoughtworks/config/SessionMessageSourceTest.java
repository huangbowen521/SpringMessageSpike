package com.thoughtworks.config;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Locale;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: twer
 * Date: 5/29/13
 * Time: 3:40 PM
 */
public class SessionMessageSourceTest {

    private SessionMessageSource sessionMessageSource;
    private ResourceBundleMessageSource parentMessageSource;
    private ServletRequestAttributes requestAttributes;

    @Before
    public void setUp() throws Exception {


        sessionMessageSource = new SessionMessageSource();
        sessionMessageSource.setBasenames(new String[]{
        "brand1", "brand2"
        });
        parentMessageSource = new ResourceBundleMessageSource();
        parentMessageSource.setBasename("default");
        sessionMessageSource.setParentMessageSource(parentMessageSource);
        requestAttributes = mock(ServletRequestAttributes.class);
        RequestContextHolder.setRequestAttributes(requestAttributes);

    }

    @Test
    public void should_get_value_from_current_brand() {
        //given
        when(requestAttributes.getAttribute(anyString(),anyInt())).thenReturn("brand1");

        // when
        String message = sessionMessageSource.resolveCodeWithoutArguments("default", new Locale(""));

        // then
        assertThat(message, is("brand1"));
    }

    @Test
    public void should_get_null_when_not_found_code() {
         // given
        when(requestAttributes.getAttribute(anyString(), anyInt())).thenReturn("brand1");

        // when
        String message = sessionMessageSource.resolveCodeWithoutArguments("notExisting", new Locale(""));

        // then
        assertThat(message, nullValue());
    }
}
