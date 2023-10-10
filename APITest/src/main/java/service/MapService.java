package service;

import java.math.BigInteger;
import java.util.Map;

public interface MapService {
	BigInteger regMap(String address, String draw) throws Exception;
	Map<String,Object> viewMap(Integer num) throws Exception;
}
