package com.thoughtworks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created with IntelliJ IDEA.
 * User: twer
 * Date: 5/27/13
 * Time: 4:12 PM
 */
@Controller
@RequestMapping("/apia")
public class APIAController {

    @RequestMapping(value= "/index", method = RequestMethod.GET)
    public String onPage(ModelMap model) {
        model.put("brand","apia");
        return "index";
    }
}
