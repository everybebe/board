package com.study.board.controller;

import com.study.board.entity.UserVO;
import com.study.board.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String login(@ModelAttribute UserVO userVO, HttpSession session,
                        @RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "exception", required = false) String exception,
                        Model model) {

        UserVO loginResult = userService.login(userVO);

        if (loginResult != null) {
            session.setAttribute("loginId", loginResult.getUserId());
            return "redirect:/board/list";
        } else {
//            model.addAttribute("error", error);
//            model.addAttribute("exception", exception);
            model.addAttribute("error", "아이디나 비밀번호");
            return "error";
        }
    }


}
