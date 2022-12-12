package kr.ac.soup.repository;

import kr.ac.soup.config.web.WebConfig;
import kr.ac.soup.entity.Member;
import kr.ac.soup.entity.MemberType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitWebConfig(classes = {WebConfig.class})
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @Transactional // 테스트 끝나면 DB에 저장되는 데이터 롤백시킴
    public void 로그인_데이터_조회() {
        Member member = Member.builder()
                .email("test@test.test")
                .memberType(MemberType.USER)
                .nickname("test")
                .password("tttt")
                .build();
        memberRepository.save(member);

        Optional<Member> findMember = memberRepository.findMemberByEmailAndPassword("test@test.test", "tttt");

        assertThat(findMember.get().getId()).isEqualTo(member.getId());
    }
}