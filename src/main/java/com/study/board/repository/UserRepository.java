package com.study.board.repository;

import com.study.board.entity.UserVO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserVO,Long> {
}
