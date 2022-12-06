package kr.ac.soup.service;

import kr.ac.soup.dto.request.ReplyRequestDto;
import kr.ac.soup.dto.response.ReplyResponseDto;
import kr.ac.soup.entity.Reply;
import kr.ac.soup.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {
    private final ReplyRepository replyRepository;

    @Override
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
        return null;
    }

    @Override
    public Long updateReply(Long replyId, ReplyRequestDto replyRequestDto) {
        return null;
    }

    @Override
    public Long deleteReply(Long replyId) {
        return null;
    }
}
