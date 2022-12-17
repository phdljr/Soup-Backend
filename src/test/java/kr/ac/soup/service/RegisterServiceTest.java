package kr.ac.soup.service;

import kr.ac.soup.config.web.WebConfig;
import kr.ac.soup.dto.request.RegisterRequestDto;
import kr.ac.soup.entity.Member;
import kr.ac.soup.entity.MemberType;
import kr.ac.soup.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitWebConfig(classes = {WebConfig.class})
public class RegisterServiceTest {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("회원가입을 시도한다.")
    @Transactional
    void register() {
        RegisterRequestDto registerRequestDto = RegisterRequestDto.builder()
                .email("test@test.com")
                .nickname("test")
                .password("test1234!")
                .build();
        registerService.register(registerRequestDto);

        Member registerMember = memberRepository.findByNickname("test").get();

        assertThat(registerRequestDto.getNickname()).isEqualTo(registerMember.getNickname());
        assertThat(registerRequestDto.getEmail()).isEqualTo(registerMember.getEmail());
    }

}
