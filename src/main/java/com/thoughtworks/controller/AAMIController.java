package com.thoughtworks.controller;

import com.sun.org.apache.bcel.internal.generic.GETFIELD;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: twer
 * Date: 5/27/13
 * Time: 4:12 PM
 */
@Controller
@RequestMapping("/aami")
public class AAMIController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String onPage(HttpServletRequest request) {
        return "index";
    }
}
