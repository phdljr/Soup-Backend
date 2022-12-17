package kr.ac.soup.service;

import kr.ac.soup.dto.request.ReplyRequestDto;
import kr.ac.soup.dto.response.ReplyResponseDto;
import kr.ac.soup.entity.Board;
import kr.ac.soup.entity.Member;
import kr.ac.soup.entity.Reply;
import kr.ac.soup.repository.BoardRepository;
import kr.ac.soup.repository.MemberRepository;
import kr.ac.soup.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    @Override
    @Transactional
    public List<ReplyResponseDto> getReplyList(Long boardId) {
        List<Reply> replyList = replyRepository.findAllByBoardIdOrderByRegisterDateAsc(boardId);
        List<ReplyResponseDto> result = replyList.stream().map(reply -> ReplyResponseDto.builder()
                .replyId(reply.getId())
                .boardId(reply.getBoard().getId())
                .nickname(reply.getMember().getNickname())
                .content(reply.getContent())
                .registerDate(reply.getRegisterDate())
                .modifyDate(reply.getModifyDate())
                .build()
        ).collect(Collectors.toList());
        return result;
    }

    @Override
    public Long postReply(ReplyRequestDto replyRequestDto) {
        Optional<Member> findMember = memberRepository.findById(replyRequestDto.getMemberId());
        Member member = findMember.get();

        Optional<Board> findBoard = boardRepository.findById(replyRequestDto.getBoardId());
        Board board = findBoard.get();

        Reply reply = Reply.builder()
                .member(member)
                .board(board)
                .content(replyRequestDto.getContent())
                .build();
        board.addReply(reply);

        reply = replyRepository.save(reply);

        return reply.getId();
    }

    @Override
    public Long updateReply(Long replyId, ReplyRequestDto replyRequestDto) {
        Optional<Reply> findReply = replyRepository.findById(replyId);
        Reply reply = findReply.get();
        reply.updateReply(replyRequestDto.getContent());
        return reply.getId();
    }

    @Override
    public Long deleteReply(Long replyId) {
        Optional<Reply> findReply = replyRepository.findById(replyId);
        Reply reply = findReply.get();

        replyRepository.deleteById(reply.getId());
        return reply.getId();
    }
}
