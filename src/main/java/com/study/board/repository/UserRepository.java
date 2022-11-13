package com.study.board.repository;

import com.study.board.entity.UserVO;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserVO,Long> {
    // select * from uservo_table where user_id = ?
    // 리턴타입 : UserVO
    // 매개변수 : userId

    Optional<UserVO> findByUserId(String userId);
}
