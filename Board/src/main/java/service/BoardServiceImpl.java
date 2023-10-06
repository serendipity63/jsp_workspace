package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

import dao.BoardDao;
import dao.BoardDaoImpl;
import dto.Board;
import util.PageInfo;

public class BoardServiceImpl implements BoardService {
	private BoardDao boardDao;

	public BoardServiceImpl() {
		boardDao = new BoardDaoImpl();
	}

	@Override
	public void boardWrite(Board board) throws Exception {
		boardDao.insertBoard(board);
	}

	@Override
	public Map<String, Object> boardListByPage(Integer page) throws Exception {
		PageInfo pageInfo = new PageInfo();
		int boardCount = boardDao.selectBoardCount();
		int maxPage = (int) Math.ceil((double) boardCount / 10);
		int startPage = (page - 1) / 10 * 10 + 1; // 1, 11,21,31...
		int endPage = startPage + 10 - 1; // or +9> 10, 20, 30...
		if (endPage > maxPage)
			endPage = maxPage;
		if (page > maxPage)
			page = maxPage;

		pageInfo.setAllPage(maxPage);
		pageInfo.setCurPage(page);
		pageInfo.setStartPage(startPage);
		pageInfo.setEndPage(endPage);

		int row = (page - 1) * 10 + 1; // 현재 페이지의 시작 row
		List<Board> boardList = boardDao.selectBoardList(row - 1);

		Map<String, Object> map = new HashMap<>();
		map.put("pageInfo", pageInfo);
		map.put("boardList", boardList);

		return map;
	}

	@Override
	public Board boardDetail(Integer num) throws Exception {
		boardDao.updateBoardViewCount(num);
		return boardDao.selectBoard(num);

	}

	@Override
	public void boardModify(Board board) throws Exception {
		boardDao.updateBoard(board);

	}

	@Override
	public void boardRemove(Integer num) throws Exception {
		boardDao.deleteBoard(num);

	}

	@Override
	public Map<String, Object> boardSearch(String type, String keyword, Integer page) throws Exception {
		Map<String, Object> param = new HashMap<>();
		param.put("type", type);
		param.put("keyword", keyword);

		PageInfo pageInfo = new PageInfo();
		int boardCount = boardDao.searchBoardCount(param);
		int maxPage = (int) Math.ceil((double) boardCount / 10);
		int startPage = (page - 1) / 10 * 10 + 1; // 1, 11,21,31...
		int endPage = startPage + 10 - 1; // or +9> 10, 20, 30...
		if (endPage > maxPage)
			endPage = maxPage;
		if (page > maxPage)
			page = maxPage;

		pageInfo.setAllPage(maxPage);
		pageInfo.setCurPage(page);
		pageInfo.setStartPage(startPage);
		pageInfo.setEndPage(endPage);

		Map<String, Object> map = new HashMap<>();
		map.put("pageInfo", pageInfo);

		if (page == 0) {
			return map;

		}

		int row = (page - 1) * 10 + 1; // 현재 페이지의 시작 row
		param.put("row", row - 1);
		List<Board> boardList = boardDao.searchBoardList(param);

		map.put("boardList", boardList);
		map.put("type", type);
		map.put("keyworde", keyword);

		return map;
	}

	@Override
	public String boardLike(String id, Integer num) throws Exception {
		Map<String, Object> param = new HashMap<>();
		param.put("id", id);
		param.put("num", num);
		// 왜 오브젝으를 할까? st in 포함하는게 obj
		// 1. boardlike 테이블에 데이터 있는지 확인(member_id, board_num)
		Integer likenum = boardDao.selectBoardLike(param);

		Map<String, Object> res = new HashMap<>();
		// 2-1. 없으면 boardlike에 삽입
		if (likenum == null) {
			boardDao.insertBoardLike(param);
			boardDao.plusBoardLikeCount(num);
		} else {
			// 2-2. 있으면 boardlike에서 삭제
			boardDao.deleteBoardLike(param);
			boardDao.minusBoardLikeCount(num);
			res.put("select", false);
		}
		// 3. board 테이블에 좋아요 수 조정 (삭제:-1, 삽입:+1)

		// 4. 좋아요 수 리턴
		Integer likecount = boardDao.selectLikeCount(num);
		res.put("likecount", likecount);

		JSONObject jsonObj = new JSONObject(res);
		return jsonObj.toJSONString();
	}

}
