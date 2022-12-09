package kr.ac.soup.controller;

import kr.ac.soup.dto.request.BoardLikeRequestDto;
import kr.ac.soup.dto.response.BoardLikeResponseDto;
import kr.ac.soup.entity.Member;
import kr.ac.soup.service.BoardLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardLikeController {

    private final BoardLikeService boardLikeService;

    @PostMapping("/boardlike")
    public List<Member> doLike(BoardLikeRequestDto boardLikeRequestDto){
        BoardLikeResponseDto boardLikeResponseDto = new BoardLikeResponseDto(boardLikeService.doLike(boardLikeRequestDto));
        return boardLikeResponseDto.getLike_list();
    }

    @DeleteMapping("/boardlike")
    public List<Member> unDoLike(BoardLikeRequestDto boardLikeRequestDto){
        BoardLikeResponseDto boardLikeResponseDto = new BoardLikeResponseDto(boardLikeService.unDoLike(boardLikeRequestDto));
        return boardLikeResponseDto.getLike_list();
    }

}
