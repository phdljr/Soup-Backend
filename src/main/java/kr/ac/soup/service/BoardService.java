package kr.ac.soup.service;

import kr.ac.soup.dto.BoardPostRequestDto;
import kr.ac.soup.dto.BoardResponseDto;

import java.util.List;

public interface BoardService {
    List<BoardResponseDto> getBoardList(int page);

    BoardResponseDto getBoard(Long id);

    Long postBoard(BoardPostRequestDto boardPostRequestDto);

    Long updateBoard(Long id, BoardPostRequestDto boardPostRequestDto);

    Long deleteBoard(Long id);
}
