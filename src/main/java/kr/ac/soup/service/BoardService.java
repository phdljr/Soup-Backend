package kr.ac.soup.service;

import kr.ac.soup.dto.request.BoardPostRequestDto;
import kr.ac.soup.dto.response.BoardListPageResponseDto;
import kr.ac.soup.dto.response.BoardResponseDto;

import javax.management.InstanceNotFoundException;

public interface BoardService {
    BoardListPageResponseDto getBoardList(int page);

    BoardResponseDto getBoard(Long boardId);

    Long postBoard(BoardPostRequestDto boardPostRequestDto);

    Long updateBoard(Long boardId, BoardPostRequestDto boardPostRequestDto);

    Long deleteBoard(Long boardId);
}
