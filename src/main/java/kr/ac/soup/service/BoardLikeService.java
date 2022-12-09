package kr.ac.soup.service;

import kr.ac.soup.dto.request.BoardLikeRequestDto;
import kr.ac.soup.entity.Member;

import java.util.List;

public interface BoardLikeService {
     boolean doLikeCheck(BoardLikeRequestDto boardLikeRequestDto);

     boolean undoLikeCheck(BoardLikeRequestDto boardLikeRequestDto);

     List<Member> doLike(BoardLikeRequestDto boardLikeRequestDto);
     List<Member> unDoLike(BoardLikeRequestDto boardLikeRequestDto);

     List<Member> getMembers(BoardLikeRequestDto boardLikeRequestDto);
}
