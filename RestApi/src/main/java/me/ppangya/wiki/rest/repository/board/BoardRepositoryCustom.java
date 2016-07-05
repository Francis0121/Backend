package me.ppangya.wiki.rest.repository.board;

import me.ppangya.wiki.rest.repository.entity.Board;

import java.util.Optional;
import java.util.stream.Stream;

public interface BoardRepositoryCustom {

    /**
     * QueryDsl Example Code (TODO : 다른 코드에서 QueryDsl 사용시 삭제)
     *
     * @param title
     * @return
     */
    Stream<Board> findListByTitleOrderByBoardIdDesc(String title);

    /**
     * Nativee Query Exampe Code (TODO : 다른 코드에서 Native 쿼리 사용시 삭제)
     *
     * @param boardId
     * @return
     */
    Optional<Board> findOneByBoardIdOrderByBoardIdAsc(Long boardId);
}
