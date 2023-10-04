package service;

import java.util.Map;

import dto.Board;

public interface BoardService {
	void boardWrite(Board board) throws Exception;

	Map<String, Object> boardListByPage(Integer page) throws Exception;
	// 왜 맵으로 줬나면 하나가 아니라 두가지를 같이 다뤄야 해서
}
