package kr.ac.soup.sercvice;

import kr.ac.soup.dto.RegisterDTO;

public interface RegisterService {
    void Register(RegisterDTO registerDTO);
    boolean reduplicationEmailCheck(RegisterDTO registerDTO);

    boolean reduplicationNicknameCheck(RegisterDTO registerDTO);
}
