package kr.ac.soup.service;

import kr.ac.soup.config.web.WebConfig;
import kr.ac.soup.dto.request.ReplyRequestDto;
import kr.ac.soup.dto.response.ReplyResponseDto;
import kr.ac.soup.entity.Board;
import kr.ac.soup.entity.Member;
import kr.ac.soup.entity.MemberType;
import kr.ac.soup.entity.Reply;
import kr.ac.soup.repository.BoardRepository;
import kr.ac.soup.repository.MemberRepository;
import kr.ac.soup.repository.ReplyRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitWebConfig(classes = {WebConfig.class})
class ReplyServiceImplTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private ReplyRepository replyRepository;
    @Autowired
    private ReplyService replyService;

    private Member member;
    private Board board;
    private Reply reply;

    @Test
    @DisplayName("게시글에 등록된 댓글의 개수를 가져온다.")
    @Transactional
    public void getReplyList() {
        createDummyData();
        List<ReplyResponseDto> replyList = replyService.getReplyList(board.getId());

        assertThat(replyList.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("게시글에 댓글을 등록한다.")
    @Transactional
    public void postReply(){
        createDummyData();
        ReplyRequestDto dto = ReplyRequestDto.builder()
                .boardId(board.getId())
                .memberId(member.getId())
                .content("postTest").build();
        Long postReplyId = replyService.postReply(dto);

        Reply findReply = replyRepository.findById(postReplyId).get();

        assertThat(findReply.getId()).isEqualTo(postReplyId);
        assertThat(findReply.getBoard().getId()).isEqualTo(dto.getBoardId());
        assertThat(findReply.getMember().getId()).isEqualTo(dto.getMemberId());
        assertThat(findReply.getContent()).isEqualTo(dto.getContent());
    }

    @Test
    @DisplayName("게시글에 등록된 댓글을 수정한다.")
    @Transactional
    public void updateReply(){
        createDummyData();
        ReplyRequestDto dto = ReplyRequestDto.builder()
                .boardId(board.getId())
                .memberId(member.getId())
                .content("updateContent").build();
        Long updateReplyId = replyService.updateReply(reply.getId(), dto);

        Reply findReply = replyRepository.findById(updateReplyId).get();

        assertThat(findReply.getContent()).isEqualTo(dto.getContent());
    }

    @Test
    @DisplayName("게시글에 등록된 댓글을 삭제한다.")
    @Transactional
    public void deleteReply(){
        createDummyData();
        Long deleteReplyId = replyService.deleteReply(reply.getId());

        Optional<Reply> result = replyRepository.findById(deleteReplyId);

        assertThat(result).isEmpty();
    }

    private void createDummyData() {
        member = Member.builder()
                .email("test@test.test")
                .memberType(MemberType.USER)
                .nickname("testNickname")
                .password("testPassword")
                .build();
        member = memberRepository.save(member);
        board = Board.builder()
                .member(member)
                .title("testTitle")
                .content("testBoardContent")
                .build();
        board = boardRepository.save(board);
        reply = Reply.builder()
                .board(board)
                .member(member)
                .content("testReplyContent")
                .build();
        reply = replyRepository.save(reply);
    }
}