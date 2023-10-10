package dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import util.MybatisSqlSessionFactory;

public class MapDaoImpl implements MapDao {
	SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();

	@Override
	public void insertMap(Map<String, Object> param) throws Exception {
		sqlSession.insert("mapper.map.insertMap", param);
		sqlSession.commit();
	}

	@Override
	public Map<String, Object> selectMap(Integer num) throws Exception {
		return sqlSession.selectOne("mapper.map.selectMap", num);
	}

}
