package kr.ac.soup.service;

import kr.ac.soup.dto.RegisterDTO;

public interface RegisterService {
    void Register(RegisterDTO registerDTO);

    boolean reduplicationEmailCheck(RegisterDTO registerDTO);

    boolean reduplicationNicknameCheck(RegisterDTO registerDTO);
}
