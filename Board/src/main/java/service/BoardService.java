package service;

import java.util.Map;

import dto.Board;

//하나의 서비스에 여러개의 쿼리문이 존재한다
public interface BoardService {
	void boardWrite(Board board) throws Exception;

	Map<String, Object> boardListByPage(Integer page) throws Exception;
	// 왜 맵으로 줬나면 하나가 아니라 두가지를 같이 다뤄야 해서

	Board boardDetail(Integer num) throws Exception;

	void boardModify(Board board) throws Exception;

	void boardRemove(Integer num) throws Exception;

	Map<String, Object> boardSearch(String type, String keyword, Integer page) throws Exception;

	String boardLike(String id, Integer num) throws Exception;

	Boolean isBoardLike(String id, Integer num) throws Exception;

}
