package kr.ac.soup.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplyResponseDto {
    private Long replyId;
    private Long boardId;
    private String nickname;
    private String content;
    private LocalDateTime registerDate;
    private LocalDateTime modifyDate;
}
