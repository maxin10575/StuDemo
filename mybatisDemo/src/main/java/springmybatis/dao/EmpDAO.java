package springmybatis.dao;

import entity.Emp;
import entity.Emp2;

import java.util.List;
import java.util.Map;

/**
 * Mapper映射器
 *
 */

public interface EmpDAO {
	void save(Emp emp);
	List<Emp> findAll();
	Emp findById(int id);
	void modify(Emp emp);
	void delete(int id);
	Map<?, ?> findById2(int id);
	Emp2 findById3(int id);
	
}




