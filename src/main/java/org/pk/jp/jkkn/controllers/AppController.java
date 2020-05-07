package org.pk.jp.jkkn.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin
@Controller
public class AppController {

    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    @ResponseBody
    public String ping() {
        return "pong!";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public void indexGet(HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Location", "index.html");
        httpServletResponse.setStatus(302);
    }

}
