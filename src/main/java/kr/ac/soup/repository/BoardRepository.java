package kr.ac.soup.repository;

import kr.ac.soup.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
