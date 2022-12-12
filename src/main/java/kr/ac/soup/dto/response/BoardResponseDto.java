package kr.ac.soup.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardResponseDto {
    private Long id;
    private String nickname;
    private String title;
    private String content;
    private LocalDateTime registerDate;
    private List<ReplyResponseDto> replies;
}
