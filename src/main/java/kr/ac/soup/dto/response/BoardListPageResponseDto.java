package kr.ac.soup.dto.response;

import kr.ac.soup.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardListPageResponseDto {
    private int totalPageNumber;
    private int currentPageNumber;
    private boolean previousPage;
    private boolean nextPage;
    private List<Integer> pageList;
    private List<BoardResponseDto> boardList;

    public BoardListPageResponseDto(Page<Board> page) {
        makePageList(page);
    }

    private void makePageList(Page<Board> page) {
        totalPageNumber = page.getTotalPages();
        currentPageNumber = page.getNumber() + 1;
        boardList = makeBoardListDto(page);

        int tempEnd = (int)Math.ceil(currentPageNumber / 10.0) * 10; // 23페이지라면 20페이지로 맞춰주기
        int start = tempEnd - 9;
        int end = Math.min(totalPageNumber, tempEnd);
        previousPage = start > 1;
        nextPage = totalPageNumber > tempEnd;
        pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
    }

    private List<BoardResponseDto> makeBoardListDto(Page<Board> page) {
        return boardList = page.getContent().stream().map(board ->
                BoardResponseDto.builder()
                        .id(board.getId())
                        .nickname(board.getMember().getNickname())
                        .title(board.getTitle())
                        .hit(board.getHit())
                        .registerDate(board.getRegisterDate())
                        .build()
        ).collect(Collectors.toList());
    }
}
