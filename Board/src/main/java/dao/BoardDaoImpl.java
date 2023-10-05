package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dto.Board;
import util.MybatisSqlSessionFactory;

public class BoardDaoImpl implements BoardDao {
	SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();

	@Override
	public void insertBoard(Board board) throws Exception {
		sqlSession.insert("mapper.board.insertBoard", board);
		sqlSession.commit();

	}

	@Override
	public List<Board> selectBoardList(Integer row) throws Exception {
		return sqlSession.selectList("mapper.board.selectBoardList", row);
	}

	@Override
	public Integer selectBoardCount() throws Exception {
		return sqlSession.selectOne("mapper.board.selectBoardCount");
	}

	@Override
	public Board selectBoard(Integer num) throws Exception {
		return sqlSession.selectOne("mapper.board.selectBoard", num);
		// namespace랑 같게해야함
	}

	@Override
	public void updateBoard(Board board) throws Exception {
		sqlSession.update("mapper.board.updateBoard", board);
		sqlSession.commit();

	}

	@Override
	public void deleteBoard(Integer num) throws Exception {
		sqlSession.delete("mapper.board.deleteBoard", num);
		sqlSession.commit();

	}

	@Override
	public Integer searchBoardCount(Map<String, Object> param) throws Exception {
		return sqlSession.selectOne("mapper.board.searchBoardCount", param);
	}

	@Override
	public List<Board> searchBoardList(Map<String, Object> param) throws Exception {
		return sqlSession.selectList("mapper.board.searchBoardList", param);
	}

}
