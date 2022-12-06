package kr.ac.soup.repository;

import kr.ac.soup.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findAllByBoardIdOrderByRegisterDateAsc(Long boardId);
}
