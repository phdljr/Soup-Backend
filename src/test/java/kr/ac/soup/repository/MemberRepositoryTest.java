package kr.ac.soup.repository;

import kr.ac.soup.config.AppConfig;
import kr.ac.soup.config.SoupWebApplicationInitializer;
import kr.ac.soup.config.WebConfig;
import kr.ac.soup.entity.Member;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SoupWebApplicationInitializer.class, AppConfig.class, WebConfig.class})
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void login_이메일_비번_입력(){
        Optional<Member> findMember = memberRepository.findMemberByEmailAndPassword("test", "tttt");
        System.out.println(findMember.get().getEmail());
    }
}