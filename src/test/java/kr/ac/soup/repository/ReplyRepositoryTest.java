package kr.ac.soup.repository;

import kr.ac.soup.config.web.WebConfig;
import kr.ac.soup.entity.Reply;
import kr.ac.soup.setup.SetUpTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitWebConfig(classes = {WebConfig.class})
class ReplyRepositoryTest extends SetUpTest {

//    @Test
//    @DisplayName("게시글에 등록된 댓글의 개수를 가져온다.")
//    @Transactional
//    void findAllByBoardIdOrderByRegisterDateAsc() {
//        createDummyDataList();
//        List<Reply> replyList = replyRepository.findAllByBoardIdOrderByRegisterDateAsc(1L);
//
//        assertThat(replyList.size()).isEqualTo(5);
//    }
}