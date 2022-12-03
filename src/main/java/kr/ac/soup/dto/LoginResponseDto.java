package kr.ac.soup.dto;

import kr.ac.soup.entity.MemberType;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {
    private Long memberId;
    private String nickname;
    private MemberType memberType;
}
