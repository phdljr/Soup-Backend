package kr.ac.soup.service;

import kr.ac.soup.config.WebConfig;
import kr.ac.soup.dto.BoardResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import java.util.List;

@SpringJUnitWebConfig(classes = {WebConfig.class})
class BoardServiceTest {

    @Autowired
    private BoardService boardService;

    @Test
    void getBoardList() {
        List<BoardResponseDto> boardList = boardService.getBoardList();
        boardList.forEach(System.out::println);
    }
}