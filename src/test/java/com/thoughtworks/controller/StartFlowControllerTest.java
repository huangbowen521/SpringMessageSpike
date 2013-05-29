package com.thoughtworks.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: twer
 * Date: 5/29/13
 * Time: 3:20 PM
 */
public class StartFlowControllerTest {

    private StartFlowController startFlowController = new StartFlowController();

    private HttpServletRequest request;

    private HttpSession session;

    @Before
    public void setUp() {
        request = Mockito.mock(HttpServletRequest.class);
        session = Mockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
    }


    @Test
    public void shouldOnAAMIPage() {
        // when
        String view = startFlowController.onAAMIPage(request);

        // then
        assertThat(view, is("redirect:aami/index"));
    }

    @Test
    public void shouldOnAPIAPage() {

        // when
        String view = startFlowController.onAPIAPage(request);

        // then
        assertThat(view, is("redirect:apia/index"));

    }
}
