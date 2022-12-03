package kr.ac.soup.dto;

import lombok.*;

@ToString
@NoArgsConstructor
public class LoginRequestDto {
    private String email;
    private String password;
}