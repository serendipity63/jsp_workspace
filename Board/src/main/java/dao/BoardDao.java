package dao;

import java.util.List;
import java.util.Map;

import dto.Board;

public interface BoardDao {
	void insertBoard(Board board) throws Exception;

	List<Board> selectBoardList(Integer row) throws Exception;
	// board.xml에서 호출

	Integer selectBoardCount() throws Exception;

	Board selectBoard(Integer num) throws Exception;

	void updateBoard(Board board) throws Exception;

	void deleteBoard(Integer num) throws Exception;

	Integer searchBoardCount(Map<String, Object> param) throws Exception;

	List<Board> searchBoardList(Map<String, Object> param) throws Exception;

}
