package com.study.board.service;


import com.study.board.entity.UserVO;
import com.study.board.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserService{

    @Autowired
    UserRepository userRepository;

    //회원가입 db저장
    public void joinUser(UserVO vo) {

        userRepository.save(vo);
    }
    //로그인
    public UserVO login(UserVO userVO) {

        /**
         *  login.html 에서 이메일, 비번을 받아오고
         *  DB로 부터 해당 이메일의 정보를 가져와서
         *  입력 받은 비번과 DB에서 조회한 비번의 일치여부를 판단하여
         *  일치하면 로그인 성공, 일치하지 않으면 로그인 실패로 처리
         */

        Optional<UserVO> optionalUserVO = userRepository.findByUserId(userVO.getUserId());

        if(optionalUserVO.isPresent()) {
            UserVO loginEntity = optionalUserVO.get();
            if (loginEntity.getPassword().equals(userVO.getPassword())) {
                return UserVO.toUserVO(loginEntity);
            } else {
                return null;
            }
        } else {
            return null;
        }

    }



}
