package kr.ac.soup.controller;

import kr.ac.soup.dto.request.ReplyRequestDto;
import kr.ac.soup.dto.response.ReplyResponseDto;
import kr.ac.soup.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @GetMapping("/reply/{boardId}")
    public List<ReplyResponseDto> getReply(@PathVariable Long boardId){
        List<ReplyResponseDto> replyList = replyService.getReplyList(boardId);
        return replyList;
    }

    @PostMapping("/reply")
    public Long postReply(@RequestBody ReplyRequestDto replyRequestDto){
        Long result = replyService.postReply(replyRequestDto);
        return result;
    }

    @PostMapping("/reply/{replyId}")
    public Long updateReply(@PathVariable Long replyId, @RequestBody ReplyRequestDto replyRequestDto){
        Long result = replyService.updateReply(replyId, replyRequestDto);
        return result;
    }

    @DeleteMapping("/reply{replyId}")
    public Long deleteReply(@PathVariable Long replyId){
        Long result = replyService.deleteReply(replyId);
        return result;
    }
}
