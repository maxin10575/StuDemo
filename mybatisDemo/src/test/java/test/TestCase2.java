package test;

import mapper.EmpMapper;
import entity.Emp;
import entity.Emp2;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class TestCase2 {
	private SqlSession session;
	private EmpMapper dao;

	@Before
	public void init() {

		SqlSessionFactoryBuilder ssfb = new SqlSessionFactoryBuilder();
		SqlSessionFactory ssf = ssfb.build(TestCase.class.getClassLoader().getResourceAsStream("SqlMapConfig.xml"));
		session = ssf.openSession();
		/*
		 * getMapper鏂规硶锛歮ybatis渚濇嵁Mapper鏄犲皠鍣� ,鐢熸垚绗﹀悎鍏惰姹傜殑瀵硅薄銆�
		 */
		dao = session.getMapper(EmpMapper.class);
	}

	@Test
	public void test1() {
		Emp emp = new Emp();
		emp.setEname("John");
		emp.setAge(22);
		dao.save(emp);
		session.commit();
		session.close();
	}

	@Test
	public void test2() {
		List<Emp> emps = dao.findAll();
		System.out.println(emps);
		session.close();
	}

	@Test
	public void test3() {
		Emp emp = dao.findById(41);
		System.out.println(emp);
		session.close();
	}

	@Test
	public void test4() {
		Emp emp = dao.findById(41);
		emp.setAge(emp.getAge() + 20);
		dao.modify(emp);
		session.commit();
		session.close();
	}

	@Test
	public void test5() {
		dao.delete(41);
		session.commit();
		session.close();
	}

	@Test
	public void test6() {
		Map map = dao.findById2(23);
		System.out.println(map);
		session.close();
	}

	@Test
	public void test7() {
		Emp2 emp = dao.findById3(23);
		System.out.println(emp);
		session.close();
	}

}
