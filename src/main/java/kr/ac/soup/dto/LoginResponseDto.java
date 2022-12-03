package kr.ac.soup.dto;

import kr.ac.soup.entity.MemberType;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto {
    private Long memberId;
    private String nickname;
    private MemberType memberType;
}
