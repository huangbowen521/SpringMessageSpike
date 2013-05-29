package com.thoughtworks.controller;

import org.junit.Test;

import javax.servlet.http.HttpServletRequest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.isNotNull;
import static org.mockito.Mockito.mock;

/**
 * Created with IntelliJ IDEA.
 * User: twer
 * Date: 5/29/13
 * Time: 3:38 PM
 */
public class APIAControllerTest {
    private HttpServletRequest request;

    @Test
    public void should_go_to_index() {
        //given
        APIAController apiaController = new APIAController();
        request = mock(HttpServletRequest.class);

        // when
        String view = apiaController.onPage(request);

        // then
        assertThat(view, is("index"));

    }
}
