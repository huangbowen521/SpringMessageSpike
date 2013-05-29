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
 * Time: 3:34 PM
 */
public class AAMIControllerTest {

    private AAMIController aamiController = new AAMIController();
    private HttpServletRequest request;

    @Test
    public void should_bind_to_index() {
        //given
        request = mock(HttpServletRequest.class);

        // when
        String view = aamiController.onPage(request);

        // then
        assertThat(view, is("index"));


    }
}
