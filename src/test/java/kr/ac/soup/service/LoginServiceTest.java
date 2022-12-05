package kr.ac.soup.service;

import kr.ac.soup.config.WebConfig;
import kr.ac.soup.dto.LoginResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

@SpringJUnitWebConfig(classes = {WebConfig.class})
class LoginServiceTest {

    @Autowired
    private LoginService loginService;

    @Test
    void 로그인_테스트(){
        LoginResponseDto dto = loginService.login("test@test.test", "tttt");
        System.out.println(dto.getMemberId());
    }
}