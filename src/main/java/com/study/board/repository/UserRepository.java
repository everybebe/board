package com.study.board.repository;

import com.study.board.entity.UserVO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserVO,Long> {

        // select * from uservo_table where user_id = ?
        // 리턴타입 : UserVO
        // 매개변수 : userId

        Optional<UserVO> findByUserId(String userId);

        @Query(value = "SELECT * FROM UserVO WHERE userId = :userId AND password = :password", nativeQuery = true)
        UserVO login(@Param("userId") String userId, @Param("password") String password);  // 영속성 컨텍스트가 해주는것

}