package kr.ac.soup.service;

import kr.ac.soup.dto.LoginResponseDto;
import kr.ac.soup.entity.Member;
import kr.ac.soup.entity.MemberType;
import kr.ac.soup.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService{

    private final MemberRepository memberRepository;

    @Override
    public LoginResponseDto login(String email, String password) {
        Optional<Member> findMember = memberRepository.findMemberByEmailAndPassword(email, password);
        if(findMember.isPresent()){
            Member member = findMember.get();
            LoginResponseDto loginResponseDto = LoginResponseDto.builder()
                    .memberId(member.getId())
                    .nickname(member.getNickname())
                    .memberType(member.getMemberType())
                    .build();
            return loginResponseDto;
        }
        return null;
    }
}
