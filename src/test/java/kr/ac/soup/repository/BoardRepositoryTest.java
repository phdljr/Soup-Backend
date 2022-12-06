package kr.ac.soup.repository;

import kr.ac.soup.config.web.WebConfig;
import kr.ac.soup.dto.request.ReplyRequestDto;
import kr.ac.soup.entity.Board;
import kr.ac.soup.entity.Member;
import kr.ac.soup.entity.MemberType;
import kr.ac.soup.entity.Reply;
import kr.ac.soup.service.ReplyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.stream.IntStream;

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
    public void setUp(){
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
    public void addReply_OneToMany_테스트(){
        // 댓글 10개 추가
        IntStream.range(1, 11).forEach(i->{
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
}