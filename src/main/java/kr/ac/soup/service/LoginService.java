package kr.ac.soup.service;

import kr.ac.soup.dto.response.LoginResponseDto;

public interface LoginService {
    LoginResponseDto login(String email, String password);
}
