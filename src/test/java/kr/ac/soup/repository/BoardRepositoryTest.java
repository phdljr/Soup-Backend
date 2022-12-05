package kr.ac.soup.repository;

import kr.ac.soup.config.AppConfig;
import kr.ac.soup.entity.Board;
import kr.ac.soup.entity.Member;
import kr.ac.soup.entity.MemberType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

@SpringJUnitConfig(classes = {AppConfig.class})
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    void 더미_데이터_생성() {
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
}