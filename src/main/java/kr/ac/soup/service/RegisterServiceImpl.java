package kr.ac.soup.service;

import kr.ac.soup.dto.request.RegisterRequestDto;
import kr.ac.soup.entity.Member;
import kr.ac.soup.entity.MemberType;
import kr.ac.soup.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    private final MemberRepository memberRepository;

    @Override
    public void register(RegisterRequestDto registerRequestDto) {
        Member member = Member.builder()
                .email(registerRequestDto.getEmail())
                .nickname(registerRequestDto.getNickname())
                .password(registerRequestDto.getPassword())
                .memberType(MemberType.USER)
                .build();

        memberRepository.save(member);
    }

    @Override
    public boolean reduplicationEmailCheck(RegisterRequestDto registerRequestDto) {
        Optional<Member> email = memberRepository.findByEmail(registerRequestDto.getEmail());
        return email.isEmpty();
    }

    @Override
    public boolean reduplicationNicknameCheck(RegisterRequestDto registerRequestDto) {
        Optional<Member> nickName = memberRepository.findByNickname(registerRequestDto.getNickname());
        return nickName.isEmpty();
    }
}
