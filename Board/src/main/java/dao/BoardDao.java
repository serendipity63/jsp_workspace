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

	void updateBoardViewCount(Integer num) throws Exception;

	Integer selectLikeCount(Integer num) throws Exception;

	void plusBoardLikeCount(Integer num) throws Exception;

	void minusBoardLikeCount(Integer num) throws Exception;

	Integer selectBoardLike(Map<String, Object> param) throws Exception;

	void insertBoardLike(Map<String, Object> param) throws Exception;

	void deleteBoardLike(Map<String, Object> param) throws Exception;
}
