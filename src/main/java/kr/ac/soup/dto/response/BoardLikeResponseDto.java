package kr.ac.soup.dto.response;

import kr.ac.soup.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardLikeResponseDto {
    private List<Member> like_list;

}
