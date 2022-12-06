package kr.ac.soup.setup;

import kr.ac.soup.config.web.WebConfig;
import kr.ac.soup.entity.Board;
import kr.ac.soup.entity.Member;
import kr.ac.soup.entity.MemberType;
import kr.ac.soup.entity.Reply;
import kr.ac.soup.repository.BoardRepository;
import kr.ac.soup.repository.MemberRepository;
import kr.ac.soup.repository.ReplyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import java.util.stream.LongStream;

@SpringJUnitWebConfig(classes = {WebConfig.class})
public class SetUpTest {

    @Autowired
    protected MemberRepository memberRepository;
    @Autowired
    protected BoardRepository boardRepository;
    @Autowired
    protected ReplyRepository replyRepository;

    protected Member member;
    protected Board board;
    protected Reply reply;

    public void createDummyData() {
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
        reply = Reply.builder()
                .board(board)
                .member(member)
                .content("testReplyContent")
                .build();
        reply = replyRepository.save(reply);
    }

    @Test
    public void createDummyDataList() {
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
