package com.study.board.controller;

import com.study.board.entity.UserVO;
import com.study.board.repository.UserRepository;
import com.study.board.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.lang.reflect.Member;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {

    @Autowired
    UserService userService;
    UserRepository userRepository;

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
    public String login(@ModelAttribute UserVO userVO, Model model, HttpServletRequest request,
                        HttpServletResponse response) {

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

        Cookie idCookie = new Cookie("memberId", String.valueOf(loginResult.getUserId()));
        response.addCookie(idCookie);

        return "redirect:/";
    }
    //로그아웃
    @PostMapping("/logout")
    public String logout(HttpServletResponse response) {
        expirCookie(response, "memberId");
        return "redirect:/";
    }
    

    private void expirCookie(HttpServletResponse response, String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

}