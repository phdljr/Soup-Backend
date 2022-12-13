package kr.ac.soup.repository;

import kr.ac.soup.config.web.WebConfig;
import kr.ac.soup.entity.Board;
import kr.ac.soup.entity.Member;
import kr.ac.soup.entity.MemberType;
import kr.ac.soup.entity.Reply;
import kr.ac.soup.setup.SetUpTest;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static org.assertj.core.api.Assertions.*;

@SpringJUnitWebConfig(classes = {WebConfig.class})
class ReplyRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private ReplyRepository replyRepository;

    @Test
    @DisplayName("게시글에 등록된 댓글의 개수를 가져온다.")
    @Transactional
    void findAllByBoardIdOrderByRegisterDateAsc() {
        createDummyDataList();
        List<Reply> replyList = replyRepository.findAllByBoardIdOrderByRegisterDateAsc(1L);

        assertThat(replyList.size()).isEqualTo(5);
    }

    @Test
    @DisplayName("게시글을 삭제할 때, 댓글도 삭제되는지 확인한다.")
    @Transactional
    void 게시글_삭제시_댓글_삭제_확인() {
        //TODO
    }

    @Test
    @DisplayName("게시글에 적힌 댓글을 확인한다.")
    @Transactional
    void 게시글_댓글_조회기() {
        // TODO
        Board saveBoard = boardRepository.save(Board.builder()
                .title("test")
                .build());
        IntStream.rangeClosed(1, 10).forEach(i -> replyRepository.save(Reply.builder()
                .board(saveBoard)
                .content("test" + i)
                .build()));

        Optional<Board> findBoard = boardRepository.findById(saveBoard.getId());
        if (findBoard.isEmpty()) {
            fail("Board를 찾지 못함");
        }
        List<Reply> replies = findBoard.get().getReplies();
        for (int i = 0; i < replies.size(); i++) {
            System.out.println(replies.get(i).getId());
        }
//        assertThat(replies.size()).isEqualTo(10);

        replies.forEach(r -> System.out.println(r.getContent()));
        System.out.println(Hibernate.isInitialized(replies));
    }

    private void createDummyDataList() {
        LongStream.range(1, 16).forEach(i -> {
            Member member = Member.builder()
                    .email("test@test.test" + i)
                    .memberType(MemberType.USER)
                    .nickname("test" + i)
                    .password("tttt" + i)
                    .build();
            member = memberRepository.save(member);

            Board board = Board.builder()
                    .member(member)
                    .title("test" + i)
                    .content("" + i)
                    .build();
            board = boardRepository.save(board);

            Board finalBoard = board;
            Member finalMember = member;
            LongStream.range(1, 6).forEach(j -> {
                Reply reply = Reply.builder()
                        .board(finalBoard)
                        .member(finalMember)
                        .content("test")
                        .build();
                replyRepository.save(reply);
            });
        });
    }
}