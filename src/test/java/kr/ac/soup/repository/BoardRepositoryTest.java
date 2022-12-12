package kr.ac.soup.repository;

import kr.ac.soup.config.web.WebConfig;
import kr.ac.soup.dto.response.BoardListPageResponseDto;
import kr.ac.soup.entity.Board;
import kr.ac.soup.entity.Member;
import kr.ac.soup.entity.MemberType;
import kr.ac.soup.entity.Reply;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitWebConfig(classes = {WebConfig.class})
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ReplyRepository replyRepository;

    private Board board;
    private Member member;

    @BeforeEach
    public void setUp() {
        member = Member.builder()
                .email("test@test.test")
                .memberType(MemberType.USER)
                .nickname("testNickname")
                .password("testPassword")
                .build();
        member = memberRepository.save(member);
        board = Board.builder()
                .member(member)
                .title("testTitle")
                .content("testContent")
                .build();
        board = boardRepository.save(board);
        Reply reply = Reply.builder()
                .board(board)
                .member(member)
                .content("testReplyContent")
                .build();
        replyRepository.save(reply);
    }

    @Test
    @DisplayName("게시글에 등록된 댓글 리스트를 가져와 개수를 비교한다.")
    @Transactional
    public void addReply_OneToMany_테스트() {
        // 댓글 10개 추가
        IntStream.range(1, 11).forEach(i -> {
            Reply reply = Reply.builder()
                    .board(board)
                    .member(member)
                    .content("testContent")
                    .build();
            board.addReply(reply);
            replyRepository.save(reply);
        });

        Board findBoard = boardRepository.findById(board.getId()).get();

        assertThat(findBoard.getReplies().size()).isEqualTo(10);
    }

    @Test
    @DisplayName("(학습 테스트)페이지 목록과 정보를 가져온다.")
    @Transactional
    public void 페이지_목록_및_정보_가져오기() {
        Page<Board> page = boardRepository.findAll(PageRequest.of(0, 10, Sort.by("registerDate").descending()));
        System.out.println("=======================================================");
        System.out.println(page.getContent());
        System.out.println("page : " + page);
        System.out.println("totalElements : " + page.getTotalElements());
        System.out.println("totalPages : " + page.getTotalPages());
        System.out.println("nextPage : " + page.hasNext());
        System.out.println("previousPage : " + page.hasPrevious());
        System.out.println("pageNumber : " + page.getNumber());
        System.out.println("=======================================================");
    }

    @ParameterizedTest
    @CsvSource(value = {"1:10:15", "2:10:15", "5:1:20"}, delimiter = ':')
    @DisplayName("페이지네이션을 적용한 BoardListPageResponseDto의 내용을 확인한다.")
    @Transactional
    public void 페이지_목록_DTO_확인(int pageNum, int size, int dataNum) {
        LongStream.rangeClosed(1, dataNum).forEach(i -> {
            Board board = Board.builder()
                    .member(member)
                    .title("test" + i)
                    .content("" + i)
                    .build();
            boardRepository.save(board);
        });

        Page<Board> page = boardRepository.findAll(PageRequest.of(pageNum - 1, size, Sort.by("registerDate").descending()));
        BoardListPageResponseDto dto = new BoardListPageResponseDto(page);

        assertThat(dto.getCurrentPageNumber()).isEqualTo(pageNum);
        System.out.println(dto.getTotalPageNumber());
        System.out.println(dto.getCurrentPageNumber());
        System.out.println(dto.getPageList());
        System.out.println(dto.isNextPage());
        System.out.println(dto.isPreviousPage());
        dto.getBoardList().forEach(d -> System.out.println(d.getTitle()));
    }
}