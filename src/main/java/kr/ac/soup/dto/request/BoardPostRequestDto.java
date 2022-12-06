package kr.ac.soup.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardPostRequestDto {
    private Long memberId;
    private String title;
    private String content;
}
