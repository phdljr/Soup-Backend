package kr.ac.soup.service;

import kr.ac.soup.dto.request.BoardPostRequestDto;
import kr.ac.soup.dto.response.BoardListPageResponseDto;
import kr.ac.soup.dto.response.BoardResponseDto;
import kr.ac.soup.dto.response.ReplyResponseDto;
import kr.ac.soup.entity.Board;
import kr.ac.soup.entity.Member;
import kr.ac.soup.repository.BoardRepository;
import kr.ac.soup.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.InstanceNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @Override
    public BoardListPageResponseDto getBoardList(int page) {
        PageRequest pageable = PageRequest.of(page - 1, 10, Sort.by(Sort.Direction.DESC, "registerDate"));
        Page<Board> boardList = boardRepository.findAll(pageable);

        BoardListPageResponseDto dto = new BoardListPageResponseDto(boardList);
        return dto;
    }

    @Override
    @Transactional // Proxy를 초기화시킬려면 Repository의 Transaction을 끌고와야 함
    public BoardResponseDto getBoard(Long boardId) {
        Optional<Board> findBoard = boardRepository.findById(boardId);
        if (findBoard.isEmpty()) {
            throw new NullPointerException("게시글을 찾지 못하였습니다.");
        }
        Board board = findBoard.get();

        List<ReplyResponseDto> repliesDto = new ArrayList<>();
        board.getReplies().forEach(reply -> {
                    ReplyResponseDto replyDto = ReplyResponseDto.builder()
                            .boardId(board.getId())
                            .replyId(reply.getId())
                            .registerDate(reply.getRegisterDate())
                            .modifyDate(reply.getModifyDate())
                            .nickname(reply.getMember().getNickname())
                            .content(reply.getContent())
                            .build();
                    repliesDto.add(replyDto);
                }
        );

        return BoardResponseDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .registerDate(board.getRegisterDate())
                .replies(repliesDto)
                .build();
    }

    @Override
    public Long postBoard(BoardPostRequestDto boardPostRequestDto) {
        Optional<Member> findMember = memberRepository.findById(boardPostRequestDto.getMemberId());
        if (findMember.isEmpty()) {
            throw new NullPointerException("유저를 찾지 못하였습니다.");
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
    public Long updateBoard(Long boardId, BoardPostRequestDto boardPostRequestDto) {
        Optional<Board> findBoard = boardRepository.findById(boardId);
        if (findBoard.isEmpty()) {
            throw new NullPointerException("게시글을 찾지 못하였습니다.");
        }

        Board board = findBoard.get();
        board.updateBoard(boardPostRequestDto.getTitle(), boardPostRequestDto.getContent());
        return board.getId();
    }

    @Override
    public Long deleteBoard(Long boardId) {
        Optional<Board> findBoard = boardRepository.findById(boardId);
        if (findBoard.isEmpty()) {
            throw new NullPointerException("게시글을 찾지 못하였습니다.");
        }
        Board board = findBoard.get();
        board.deleteBoard();
        boardRepository.delete(board);
        return boardId;
    }
}
