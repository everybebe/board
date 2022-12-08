package com.study.board.controller;

import com.study.board.entity.UserVO;
import com.study.board.repository.UserRepository;
import com.study.board.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
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

//    @PostMapping("/login")
//    public String login(@ModelAttribute UserVO userVO, HttpSession session,
//                        Model model) {
//
//        UserVO loginResult = userService.login(userVO);
//
//        if (loginResult != null) {
//            session.setAttribute("loginId", loginResult);
//
//            return "redirect:/board/list";
//        } else {
//            model.addAttribute("error", "아이디 또는 비밀번호를 잘못 입력했습니다.");
//            model.addAttribute("errorMessage", "/login");
//
//            return "error";
//        }
//    }


    @PostMapping("/login")
    public String login(String userId, String password, HttpSession session, Model model) {

        Optional<UserVO> userVO = userRepository.findByUserId(userId);

        if ( userVO == null ) {
            System.out.println("로그인 실패");

            model.addAttribute("error", "아이디 또는 비밀번호를 잘못 입력했습니다.");
            model.addAttribute("errorMessage", "/login");

            return "error";
        }

        if ( !password.equals(userVO.get().getPassword())) {
            System.out.println("로그인 실패");

            model.addAttribute("error", "아이디 또는 비밀번호를 잘못 입력했습니다.");
            model.addAttribute("errorMessage", "/login");

            return "error";
        }

            session.setAttribute("sessionId", userVO);
            System.out.println("로그인 성공");

        return "redirect:/board/list";

    }

    //로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("sessionId");
        return "redirect:/board/list";
    }
}
