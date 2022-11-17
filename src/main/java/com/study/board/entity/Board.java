package com.study.board.entity;

import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Setter
@ToString
@RequiredArgsConstructor
//@NoArgsConstructor
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String content;

    private String name;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime date;

    private String filename;

    private String filepath;
    
    private int count;
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
//        Board board = (Board) o;
//        return id != null && Objects.equals(id, board.id);
//    }
//
//    @Override
//    public int hashCode() {
//        return getClass().hashCode();
//    }
}
