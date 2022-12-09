package kr.ac.soup.repository;

import kr.ac.soup.entity.Board;
import kr.ac.soup.entity.BoardLike;
import kr.ac.soup.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardLikeRepository extends JpaRepository<BoardLike, Long> {
    List<BoardLike> findByMember_id(Long memberId);
    List<BoardLike> findByBoard_id(Long boardId);

    Optional<BoardLike> findByBoard_idAndMember_id(Long boardId , Long memberId);
}
