package me.ppangya.wiki.rest.repository.board;

import me.ppangya.wiki.rest.repository.entity.Board;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface BoardRepository extends Repository<Board, Long>, BoardRepositoryCustom {

    Optional<Board> findOne(Long id);

    <S extends Board> Board save(S board);

    void delete(Board board);
}
