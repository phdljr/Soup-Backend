package kr.ac.soup.service;

import kr.ac.soup.dto.request.BoardPostRequestDto;
import kr.ac.soup.dto.response.BoardResponseDto;
import kr.ac.soup.entity.Board;
import kr.ac.soup.entity.Member;
import kr.ac.soup.repository.BoardRepository;
import kr.ac.soup.repository.MemberRepository;
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
    private final MemberRepository memberRepository;

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

    @Override
    public Long postBoard(BoardPostRequestDto boardPostRequestDto) {
        Optional<Member> findMember = memberRepository.findById(boardPostRequestDto.getMemberId());
        if (findMember.isEmpty()) {
            return null;
        }
        Board board = Board.builder()
                .member(findMember.get())
                .title(boardPostRequestDto.getTitle())
                .content(boardPostRequestDto.getContent())
                .build();

        // 위의 board랑 밑의 board는 서로 다른 객체라는 사실 잘 인지해두기
        board = boardRepository.save(board);

        return board.getId();
    }

    @Override
    public Long updateBoard(Long id, BoardPostRequestDto boardPostRequestDto) {
        Optional<Board> findBoard = boardRepository.findById(id);
        if(findBoard.isEmpty()){
            return null;
        }

        Board board = findBoard.get();
        board.updateBoard(boardPostRequestDto.getTitle(), boardPostRequestDto.getContent());
        return board.getId();
    }

    @Override
    public Long deleteBoard(Long id) {
        boardRepository.deleteById(id);
        return id;
    }
}
