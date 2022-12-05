package kr.ac.soup.service;

import kr.ac.soup.dto.BoardResponseDto;

import java.util.List;

public interface BoardService {
    List<BoardResponseDto> getBoardList(int page);
    BoardResponseDto getBoard(Long id);
}
