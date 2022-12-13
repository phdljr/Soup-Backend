package kr.ac.soup.service;

import kr.ac.soup.dto.request.BoardLikeRequestDto;
import kr.ac.soup.entity.Board;
import kr.ac.soup.entity.BoardLike;
import kr.ac.soup.entity.Member;
import kr.ac.soup.repository.BoardLikeRepository;
import kr.ac.soup.repository.BoardRepository;
import kr.ac.soup.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardLikeServiceImpl implements BoardLikeService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    private final BoardLikeRepository boardLikeRepository;
    @Override
    public boolean doLikeCheck(BoardLikeRequestDto boardLikeRequestDto) {
        Board board = boardRepository.findById(boardLikeRequestDto.getBoard_id()).get();

        Member member = memberRepository.findById(boardLikeRequestDto.getMember_id()).get();
        Optional<BoardLike> boardLike = boardLikeRepository.findByBoard_idAndMember_id(board.getId(), member.getId());

        if(boardLike.isPresent()){
            return false;
        }
        else {
            boardLikeRepository.save(BoardLike.builder()
                            .board(board)
                            .member(member)
                    .build());
            return true;
        }

    }

    @Override
    public boolean undoLikeCheck(BoardLikeRequestDto boardLikeRequestDto) {
        Board board = boardRepository.findById(boardLikeRequestDto.getBoard_id()).get();
        Member member = memberRepository.findById(boardLikeRequestDto.getMember_id()).get();
        Optional<BoardLike> boardLike = boardLikeRepository.findByBoard_idAndMember_id(board.getId(), member.getId());

        if(boardLike.isPresent()){
            boardLikeRepository.delete(boardLike.get());
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public List<Member> doLike(BoardLikeRequestDto boardLikeRequestDto) {
        if(doLikeCheck(boardLikeRequestDto)){
            List<BoardLike> boardLikeList = boardLikeRepository.findByBoard_id(boardLikeRequestDto.getBoard_id());
            List<Member> like_list = new ArrayList<>();
            for(int i = 0; i < boardLikeList.size(); i++){
                like_list.add(boardLikeList.get(i).getMember());
            }
            return like_list;
        }
        return null;


    }



    @Override
    public List<Member> unDoLike(BoardLikeRequestDto boardLikeRequestDto) {
        if(undoLikeCheck(boardLikeRequestDto)){
            List<BoardLike> boardLikeList = boardLikeRepository.findByBoard_id(boardLikeRequestDto.getBoard_id());
            List<Member> like_list = new ArrayList<>();
            for(int i = 0; i < boardLikeList.size(); i++){
                like_list.add(boardLikeList.get(i).getMember());
            }
            return like_list;
        }
        return null;
    }

    @Override
    public List<Member> getMembers(BoardLikeRequestDto boardLikeRequestDto) {
        if(doLikeCheck(boardLikeRequestDto)){
            List<BoardLike> boardLikeList = boardLikeRepository.findByBoard_id(boardLikeRequestDto.getBoard_id());
            List<Member> like_list = new ArrayList<>();
            for(int i = 0; i < boardLikeList.size(); i++){
                like_list.add(boardLikeList.get(i).getMember());
            }
            return like_list;
        }
        return null;
    }


}
