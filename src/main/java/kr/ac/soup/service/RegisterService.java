package kr.ac.soup.service;

import kr.ac.soup.dto.request.RegisterRequestDto;

public interface RegisterService {
    void register(RegisterRequestDto registerDTO);

    boolean reduplicationEmailCheck(RegisterRequestDto registerDTO);

    boolean reduplicationNicknameCheck(RegisterRequestDto registerDTO);
}
