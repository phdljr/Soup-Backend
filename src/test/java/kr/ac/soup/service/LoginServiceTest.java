package kr.ac.soup.service;

import kr.ac.soup.config.web.WebConfig;
import kr.ac.soup.dto.response.LoginResponseDto;
import kr.ac.soup.entity.Member;
import kr.ac.soup.entity.MemberType;
import kr.ac.soup.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitWebConfig(classes = {WebConfig.class})
class LoginServiceTest {

    @Autowired
    private LoginService loginService;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("로그인을 시도한다.")
    @Transactional
    void login() {
        Member member = Member.builder()
                .email("test@test.test")
                .memberType(MemberType.USER)
                .nickname("test")
                .password("tttt")
                .build();
        Member saveMember = memberRepository.save(member);

        LoginResponseDto dto = loginService.login(saveMember.getEmail(), saveMember.getPassword());

        assertThat(dto.getMemberId()).isEqualTo(saveMember.getId());
        assertThat(dto.getNickname()).isEqualTo(saveMember.getNickname());
    }
}