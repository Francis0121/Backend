package me.ppangya.wiki.rest.repository.board.jpa;

import com.mysema.query.jpa.impl.JPAQuery;
import me.ppangya.wiki.rest.repository.board.BoardRepositoryCustom;
import me.ppangya.wiki.rest.repository.entity.Board;
import me.ppangya.wiki.rest.repository.entity.QBoard;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class BoardRepositoryImpl implements BoardRepositoryCustom {

    private @PersistenceContext EntityManager entityManager;

    @Override
    public Stream<Board> findListByTitleOrderByBoardIdDesc(String title) {
        JPAQuery query = new JPAQuery(entityManager);
        QBoard board = new QBoard("board");
        List<Board> boardList = query.from(board).where(board.title.like("".concat(title).concat(""))).orderBy(board.boardId.desc()).list(board);
        return CollectionUtils.isEmpty(boardList) ? Stream.empty() : boardList.stream();
    }

    @Override
    public Optional<Board> findOneByBoardIdOrderByBoardIdAsc(Long boardId) {
        String query = "SELECT board_id AS boardId, title FROM BOARD WHERE board_id = ?";
        Query nativeQuery = entityManager.createNativeQuery(query, Board.class).setParameter(1, boardId);
        Object result = nativeQuery.getSingleResult();
        return result == null ? Optional.empty() : Optional.of((Board) result);
    }
}
