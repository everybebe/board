package com.study.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SampleController {

    @GetMapping("/sample")
        public String testSample() {
            return "test";
    }


    @RequestMapping(value = "loginInput", method = RequestMethod.GET)
    public String helloWorld() {
        return "loginInput";
    }

    @PostMapping("/loginOutput")
    public String loginOutput (@RequestParam(value = "name", required = false) String name,
                               @RequestParam String password, Model model) {

        System.out.println(name);
        System.out.println(password);

        model.addAttribute("name", name);
        model.addAttribute("password", password);

        return "loginOutput";
    }
}
