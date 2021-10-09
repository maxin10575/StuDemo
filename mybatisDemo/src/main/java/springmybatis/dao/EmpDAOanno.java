package springmybatis.dao;

import entity.Emp;
import entity.Emp2;
import org.springframework.stereotype.Repository;
import springmybatis.annotations.MyRepository;

import java.util.List;
import java.util.Map;

/**
 * Mapper映射器
 *
 */
@Repository("employeeDAOanno")
@MyRepository
//添加了该注解(@MyRepository)的映射器，才会被
//MapperScannerConfigurer扫描。
public interface EmpDAOanno {
	void save(Emp emp);
	List<Emp> findAll();
	Emp findById(int id);
	void modify(Emp emp);
	void delete(int id);
	Map findById2(int id);
	Emp2 findById3(int id);
	
}




