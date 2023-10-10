package service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import dao.MapDao;
import dao.MapDaoImpl;

public class MapServiceImpl implements MapService {
	private MapDao mapDao;
	public MapServiceImpl() {
		mapDao = new MapDaoImpl();
	}

	@Override
	public BigInteger regMap(String address, String draw) throws Exception {
		Map<String,Object> param = new HashMap<>();
		param.put("address", address);
		param.put("draw", draw);
		mapDao.insertMap(param);
		return (BigInteger)param.get("num");
	}

	@Override
	public Map<String, Object> viewMap(Integer num) throws Exception {
		return mapDao.selectMap(num);
	}
}
