package com.sale.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: wangzhi
 * Date: 4/15/14
 * Time: 3:21 PM
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class BaseController {
    @RequestMapping("/index")
    public String base(Model model) {
        model.addAttribute("message", "Success");
        return "index";
    }
}
