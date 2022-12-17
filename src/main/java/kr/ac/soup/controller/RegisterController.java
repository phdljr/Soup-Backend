package kr.ac.soup.controller;

import kr.ac.soup.dto.request.RegisterRequestDto;
import kr.ac.soup.service.RegisterServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterServiceImpl registerServiceImpl;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDto registerDTO) {
        if (!registerServiceImpl.reduplicationNicknameCheck(registerDTO)) {
            return ResponseEntity.badRequest().body("닉네임을 다시 설정 해주세요");
        } else if (!registerServiceImpl.reduplicationEmailCheck(registerDTO)) {
            return ResponseEntity.badRequest().body("이메일을 다시 설정 해주세요");
        } else {
            registerServiceImpl.register(registerDTO);
            return ResponseEntity.ok().body(null);
        }
    }
}
