package dao;

import java.util.Map;

public interface MapDao {
	void insertMap(Map<String,Object> param) throws Exception;
	Map<String,Object> selectMap(Integer num) throws Exception;
}
