package kr.ac.soup.service;

import kr.ac.soup.config.web.WebConfig;
import kr.ac.soup.dto.request.BoardPostRequestDto;
import kr.ac.soup.dto.response.BoardListPageResponseDto;
import kr.ac.soup.entity.Board;
import kr.ac.soup.entity.Member;
import kr.ac.soup.entity.MemberType;
import kr.ac.soup.entity.Reply;
import kr.ac.soup.repository.BoardRepository;
import kr.ac.soup.repository.MemberRepository;
import kr.ac.soup.repository.ReplyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitWebConfig(classes = {WebConfig.class})
class BoardServiceTest {

    @Autowired
    private BoardService boardService;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ReplyRepository replyRepository;

    private Member testMember;
    private Board testBoard;

    @BeforeEach
    public void setUp() {
        testMember = Member.builder()
                .id(1234L)
                .email("test@gmail.com")
                .nickname("Ted")
                .password("123456789")
                .memberType(MemberType.USER)
                .build();
        testMember = memberRepository.save(testMember);
        testBoard = Board.builder()
                .id(1234L)
                .member(testMember)
                .content("테스트 제목")
                .title("테스트 내용")
                .build();
        testBoard = boardRepository.save(testBoard);
    }

    @Test
    @DisplayName("게시글 10개를 생성일자 기준으로 내림차순해서 가져온다.")
    @Transactional
    void getBoardList() {
        createDummyData();
        BoardListPageResponseDto dto = boardService.getBoardList(1);

        assertThat(dto.getBoardList().size()).isEqualTo(16);
    }

    private void createDummyData() {
        IntStream.range(1, 16).forEach(i -> {
            Member member = Member.builder()
                    .email("test@test.test" + i)
                    .memberType(MemberType.USER)
                    .nickname("test" + i)
                    .password("tttt" + i)
                    .build();
            memberRepository.save(member);
        });

        LongStream.range(1, 16).forEach(i -> {
            Board board = Board.builder()
                    .member(memberRepository.findById(i).get())
                    .title("test" + i)
                    .content("" + i)
                    .build();
            boardRepository.save(board);
        });
    }

    @Test
    @DisplayName("게시글 등록후 다시 가져와서 값이 같은지 비교한다.")
    @Transactional
    void postBoard() {
        BoardPostRequestDto dto = BoardPostRequestDto.builder()
                .title("테스트 제목")
                .content("테스트 내용")
                .memberId(testMember.getId())
                .build();

        Long boardId = boardService.postBoard(dto);
        Board findBoard = boardRepository.findById(boardId).get();

        assertThat(boardId).isEqualTo(findBoard.getId());
        assertThat(dto.getTitle()).isEqualTo(findBoard.getTitle());
        assertThat(dto.getContent()).isEqualTo(findBoard.getContent());
    }

    @Test
    @DisplayName("게시글 등록후 업데이트하고 다시 가져와서 변경된 내용을 비교한다.")
    @Transactional
    void updateBoard() {
        BoardPostRequestDto dto = BoardPostRequestDto.builder()
                .title("테스트 제목 수정")
                .content("테스트 내용 수정")
                .memberId(testMember.getId())
                .build();
        Long updatedBoardId = boardService.updateBoard(testBoard.getId(), dto);

        Board findBoard = boardRepository.findById(updatedBoardId).get();

        assertThat(findBoard.getTitle()).isEqualTo(dto.getTitle());
        assertThat(findBoard.getContent()).isEqualTo(dto.getContent());
        assertThat(findBoard.getMember().getId()).isEqualTo(dto.getMemberId());
    }

    @Test
    @DisplayName("게시글이 삭제되었는지 확인한다.")
    @Transactional
    void deleteBoard() {
        Long boardId = boardService.deleteBoard(testBoard.getId());
        Optional<Board> findBoard = boardRepository.findById(boardId);

        assertThat(findBoard).isEmpty();
    }

    @Test
    @DisplayName("게시글을 삭제하면 연관된 댓글도 삭제되는지 확인한다.")
    @Transactional
    public void deleteBoard_reply_cascade_테스트(){
        // 댓글 10개 추가
        IntStream.range(1, 11).forEach(i->{
            Reply reply = Reply.builder()
                    .member(testMember)
                    .board(testBoard)
                    .content("cascade test")
                    .build();
            testBoard.addReply(reply);
            replyRepository.save(reply);
        });

        boardService.deleteBoard(testBoard.getId());
        assertThat(testBoard.getReplies()).isNullOrEmpty();
    }
}