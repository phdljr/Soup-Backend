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
    public List<BoardResponseDto> getBoardList() {
        PageRequest pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "registerDate"));
        Page<Board> boardList = boardRepository.findAll(pageable);

        List<BoardResponseDto> result = boardList.map(board ->
                BoardResponseDto.builder()
                        .id(board.getId())
                        .title(board.getTitle())
                        .content(board.getContent())
                        .registerDate(board.getRegisterDate())
                        .build()
        ).stream().toList();
        return result;
    }

    @Override
    public Optional<Board> getBoard(Long id) {
        return Optional.empty();
    }
}
