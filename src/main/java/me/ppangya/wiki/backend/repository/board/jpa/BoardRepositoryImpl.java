package me.ppangya.wiki.backend.repository.board.jpa;

import com.mysema.query.jpa.impl.JPAQuery;
import me.ppangya.wiki.backend.repository.board.BoardRepositoryCustom;
import me.ppangya.wiki.backend.repository.entity.Board;
import me.ppangya.wiki.backend.repository.entity.QBoard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Stream;

public class BoardRepositoryImpl implements BoardRepositoryCustom {

    private @Autowired EntityManager entityManager;

    @Override
    public Stream<Board> findListByTitleOrderByBoardIdDesc(String title) {
        JPAQuery query = new JPAQuery(entityManager);
        QBoard board = new QBoard("board");
        List<Board> boardList = query.from(board).where(board.title.like("".concat(title).concat(""))).orderBy(board.boardId.desc()).list(board);
        return CollectionUtils.isEmpty(boardList) ? Stream.empty() : boardList.stream();
    }
}
