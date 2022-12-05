package kr.ac.soup.controller;

import kr.ac.soup.dto.BoardResponseDto;
import kr.ac.soup.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board")
    public List<BoardResponseDto> boardList(){
        List<BoardResponseDto> boardList = boardService.getBoardList(1);
        return boardList;
    }
}
