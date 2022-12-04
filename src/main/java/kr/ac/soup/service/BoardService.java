package kr.ac.soup.service;

import kr.ac.soup.dto.BoardResponseDto;
import kr.ac.soup.entity.Board;

import java.util.List;
import java.util.Optional;

public interface BoardService {
    List<BoardResponseDto> getBoardList();
    Optional<Board> getBoard(Long id);
}
