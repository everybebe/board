package com.study.board.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class UserVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userSn;
    private String userId;
    private String password;
    private String userName;

    public static UserVO toUserVO(UserVO userVO) {
        UserVO userVO1 = new UserVO();
        userVO1.setUserId(userVO.getUserId());
        userVO1.setUserSn(userVO.getUserSn());
        userVO1.setUserName(userVO.getUserName());
        userVO1.setPassword(userVO.getPassword());
        return userVO1;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserVO() {

    }

    public UserVO(String userId, String password, String userName) {
        this.userId = userId;
        this.password = password;
        this.userName = userName;
    }
}
