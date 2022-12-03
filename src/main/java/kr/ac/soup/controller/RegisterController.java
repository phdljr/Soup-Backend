package kr.ac.soup.controller;

import kr.ac.soup.dto.RegisterDTO;
import kr.ac.soup.sercvice.RegisterServicempl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegisterController {


    @Autowired
    private RegisterServicempl registerServicempl;
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO registerDTO){
        if(!registerServicempl.reduplicationNicknameCheck(registerDTO)){
            return ResponseEntity.badRequest().body("닉네임을 다시 설정 해주세요");
        }
        else if(!registerServicempl.reduplicationEmailCheck(registerDTO)){
            return ResponseEntity.badRequest().body("이메일을 다시 설정 해주세요");
        }
        else{
            registerServicempl.Register(registerDTO);
            return ResponseEntity.ok().body(null);
        }
    }
}
