package test.spring.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;

public class AbstractDAO {
	
	@Resource
	protected SqlSessionTemplate sqlSession;
	
}
