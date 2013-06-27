package com.thoughtworks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class StartFlowController {

    @RequestMapping (value = "/aami", method = RequestMethod.GET)
    public String onAAMIPage(HttpServletRequest request) {
        request.getSession().setAttribute("brand", "aami");
        return "redirect:aami/index";
    }

    @RequestMapping (value = "/apia", method = RequestMethod.GET)
    public String onAPIAPage(HttpServletRequest request) {
        request.getSession().setAttribute("brand", "apia");
        return "redirect:apia/index";
    }
}
