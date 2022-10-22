package com.study.board.service;


import com.study.board.entity.UserVO;
import com.study.board.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void joinUser(UserVO vo) {

        userRepository.save(vo);
    }
}
