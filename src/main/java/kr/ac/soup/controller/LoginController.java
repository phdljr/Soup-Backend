package kr.ac.soup.controller;

import kr.ac.soup.dto.LoginRequestDto;
import kr.ac.soup.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/test/{id}")
    public String test(@PathVariable Long id){
        return id.toString();
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDto loginRequestDto){
        System.out.println(loginRequestDto);
        return loginRequestDto.toString();
    }

//    @GetMapping("/login")
//    public LoginResponseDto login(LoginRequestDto loginRequestDto){
//        System.out.println(loginRequestDto);
//        LoginResponseDto loginResponseDto = loginService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());
//        return loginResponseDto;
//    }
}
