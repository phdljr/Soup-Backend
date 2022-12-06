package kr.ac.soup.service;

import kr.ac.soup.dto.request.ReplyRequestDto;
import kr.ac.soup.dto.response.ReplyResponseDto;

import java.util.List;

public interface ReplyService {
    List<ReplyResponseDto> getReplyList(Long boardId);

    Long postReply(ReplyRequestDto replyRequestDto);

    Long updateReply(Long replyId, ReplyRequestDto replyRequestDto);

    Long deleteReply(Long replyId);
}
