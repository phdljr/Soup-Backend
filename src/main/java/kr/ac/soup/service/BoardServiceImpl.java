package kr.ac.soup.service;

import kr.ac.soup.dto.BoardResponseDto;
import kr.ac.soup.entity.Board;
import kr.ac.soup.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public List<BoardResponseDto> getBoardList(int page) {
        // 0이 1페이지로 인식
        PageRequest pageable = PageRequest.of(page - 1, 10, Sort.by(Sort.Direction.DESC, "registerDate"));
        Page<Board> boardList = boardRepository.findAll(pageable);

        return boardList.map(board ->
                BoardResponseDto.builder()
                        .id(board.getId())
                        .title(board.getTitle())
                        .registerDate(board.getRegisterDate())
                        .build()
        ).stream().toList();
    }

    @Override
    public BoardResponseDto getBoard(Long id) {
        Optional<Board> findBoard = boardRepository.findById(id);

        return findBoard.map(board ->
                BoardResponseDto.builder()
                        .id(board.getId())
                        .title(board.getTitle())
                        .content(board.getContent())
                        .registerDate(board.getRegisterDate())
                        .build()
        ).get();
    }
}
