package me.ppangya.wiki.backend.repository.board;

import me.ppangya.wiki.backend.repository.entity.Board;

import java.util.stream.Stream;

public interface BoardRepositoryCustom {

    /**
     * QueryDsl Example Code (TODO : 다른 코드에서 QueryDsl 사용시 삭제)
     *
     * @param title
     * @return
     */
    Stream<Board> findListByTitleOrderByBoardIdDesc(String title);

}
