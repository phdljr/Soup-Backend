package kr.ac.soup.controller;

import kr.ac.soup.dto.BoardResponseDto;
import kr.ac.soup.dto.LoginRequestDto;
import kr.ac.soup.dto.LoginResponseDto;
import kr.ac.soup.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board")
    public ResponseEntity<List<BoardResponseDto>> boardList(@RequestParam(required = false, defaultValue = "1") int page){
        List<BoardResponseDto> result = boardService.getBoardList(page);
        if (Objects.isNull(result)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/board/{id}")
    public ResponseEntity<BoardResponseDto> board(@PathVariable Long id){
        BoardResponseDto result = boardService.getBoard(id);
        if (Objects.isNull(result)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(result);
    }
}
