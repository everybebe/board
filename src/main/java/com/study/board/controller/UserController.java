package com.study.board.controller;

import com.study.board.entity.UserVO;
import com.study.board.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public String login(@ModelAttribute UserVO userVO, Model model, HttpServletRequest request) {

        //세션을 가져온다.
        HttpSession session = request.getSession();

        UserVO loginResult = userService.login(userVO);

        if (loginResult == null) {
            model.addAttribute("error", "아이디 또는 비밀번호를 잘못 입력했습니다.");
            model.addAttribute("errorMessage", "/login");

            return "error";

        } else {
            session.setAttribute("loginId", loginResult.getUserId());

        }
        return "redirect:/";


    }

    @PostMapping("/logout")
    public String logout(HttpServletResponse response) {
        expireCookie(response, "memberId");
        return "redirect:/";
    }

    private void expireCookie(HttpServletResponse response, String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}