package kr.ac.soup.controller;

import kr.ac.soup.dto.request.BoardPostRequestDto;
import kr.ac.soup.dto.response.BoardResponseDto;
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

    // 게시글 목록 조회
    @GetMapping("/board")
    public ResponseEntity<List<BoardResponseDto>> getBoardList(@RequestParam(required = false, defaultValue = "1") int page){
        List<BoardResponseDto> result = boardService.getBoardList(page);
        if (Objects.isNull(result)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(result);
    }

    // 게시글 조회
    @GetMapping("/board/{id}")
    public ResponseEntity<BoardResponseDto> getBoard(@PathVariable Long id){
        BoardResponseDto result = boardService.getBoard(id);
        if (Objects.isNull(result)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(result);
    }

    //게시글 등록
    @PostMapping("/board")
    public ResponseEntity<?> postBoard(@RequestBody BoardPostRequestDto boardPostRequestDto){
        Long result = boardService.postBoard(boardPostRequestDto);
        return ResponseEntity.ok(result);
    }

    // 게시글 수정
    @PutMapping("/board/{id}")
    public ResponseEntity<?> updateBoard(@PathVariable Long id, @RequestBody BoardPostRequestDto boardPostRequestDto){
        Long result = boardService.updateBoard(id, boardPostRequestDto);
        return ResponseEntity.ok(result);
    }

    // 게시글 삭제
    @DeleteMapping("/board/{id}")
    public ResponseEntity<?> deleteBoard(@PathVariable Long id){
        Long result = boardService.deleteBoard(id);
        return ResponseEntity.ok(result);
    }
}
