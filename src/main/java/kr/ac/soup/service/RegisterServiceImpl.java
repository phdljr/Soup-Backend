package kr.ac.soup.service;

import kr.ac.soup.dto.RegisterDTO;
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
    public void Register(RegisterDTO registerDTO) {
        Member member = Member.builder()
                .email(registerDTO.getEmail())
                .nickname(registerDTO.getNickname())
                .password(registerDTO.getPassword())
                .memberType(MemberType.USER)
                .build();

        memberRepository.save(member);
    }

    @Override
    public boolean reduplicationEmailCheck(RegisterDTO registerDTO) {
        Optional<Member> Email = memberRepository.findByEmail(registerDTO.getEmail());
        return Email.isEmpty();
    }

    @Override
    public boolean reduplicationNicknameCheck(RegisterDTO registerDTO) {
        Optional<Member> NickName = memberRepository.findByNickname(registerDTO.getNickname());
        return NickName.isEmpty();
    }
}
