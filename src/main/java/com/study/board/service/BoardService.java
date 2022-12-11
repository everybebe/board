package com.study.board.service;

import com.study.board.entity.Board;
import com.study.board.entity.UserVO;
import com.study.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;


    //글 작성 처리

    public void write(Board board, MultipartFile file) throws Exception {

        //만약 파일이 null 이 아니라면
        if (file != null) {
            //저장 경로 지정
            String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/files/";
            //랜덤으로 파일 이름 만들어줌
            UUID uuid = UUID.randomUUID();
            String fileName = uuid + "_" + file.getOriginalFilename();
            //파일을 넣을 껍데기 생성
            File saveFile = new File(projectPath, fileName);

            file.transferTo(saveFile);

            //DB에 값 넣기
            board.setFilename(fileName); //저장된 파일 이름
            board.setFilepath("/files/" + fileName); //저장된 파일 경로

//            board.setFilepath(board.getFilepath());
        }
            boardRepository.save(board);

    }

    //게시물 리스트 처리
    public Page<Board> boardList(Pageable pageable) {

        return boardRepository.findAll(pageable);
    }

    public Page<Board> boardSearchList(String searchKeyword, Pageable pageable) {

        return boardRepository.findByTitleContaining(searchKeyword, pageable);
    }

    //특정 게시글 불러오기
    public Board boardView(Integer id) {

        return boardRepository.findById(id).get();
    }

    @Transactional(readOnly = true)
    public static List<Board> findAllDesc() {
        return BoardRepository.findAllDesc().stream()
                .map(Board::new)
                .collect(Collectors.toList());
    }

    //특정 게시글 삭제

    public void boardDelete(Integer id) {

        boardRepository.deleteById(id);
    }



}
