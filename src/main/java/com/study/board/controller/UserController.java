package com.study.board.controller;

import com.study.board.entity.UserVO;
import com.study.board.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    //회원가입
    @GetMapping("/join")
    public String joinForm() {
        return "join";
    }

    //회원가입 확인 후 화면
    @PostMapping("/join")
    public String join(UserVO userVO) {
        userService.joinUser(userVO);
        return "redirect:/board/list";
    }

    //로그인
    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserVO userVO, HttpSession session) {
        UserVO loginResult = userService.login(userVO);
        if (loginResult != null) {
            session.setAttribute("loginId", loginResult.getUserId());
            return "redirect:/board/list";
        } else {
            return "login";
        }
    }


}
