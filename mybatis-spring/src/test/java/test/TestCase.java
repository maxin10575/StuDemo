package test;

import java.util.List;

import dao.EmpDAOanno;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.EmpDAO;
import entity.Emp;
/**
* @Description: Unit4使用Java5中的注解（annotation），以下是JUnit4常用的几个annotation：
 * @Before：初始化方法 对于每一个测试方法都要执行一次（注意与BeforeClass区别，后者是对于所有方法执行一次）
 * @After：释放资源 对于每一个测试方法都要执行一次（注意与AfterClass区别，后者是对于所有方法执行一次）
 * @Test：测试方法，在这里可以测试期望异常和超时时间
 * @Test(expected=ArithmeticException.class)检查被测方法是否抛出ArithmeticException异常
 * @Ignore：忽略的测试方法
 * @BeforeClass：针对所有测试，只执行一次，且必须为static void
 * @AfterClass：针对所有测试，只执行一次，且必须为static void
 * 一个JUnit4的单元测试用例执行顺序为：
 * @BeforeClass -> @Before -> @Test -> @After -> @AfterClass;
 * 每一个测试方法的调用顺序为：
 * @Before -> @Test -> @After;
* @Author: maxin
* @Date: 2020/3/26
* @Modified By:
* @Version: 1.0.0
*/

public class TestCase {
	private EmpDAO dao;

	@Before
	public void init() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		dao = ac.getBean("employeeDAO", EmpDAO.class);
	}

/*	@Before
	public void init() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext1.xml");
		dao = ac.getBean("employeeDAOanno", EmpDAOanno.class);
	}*/

	@Test
	public void test1() {
		Emp emp = new Emp();
		emp.setEname("King");
		emp.setAge(32);
		dao.save(emp);
	}

	@Test
	public void test2() {
		List<Emp> emps = dao.findAll();
		System.out.println(emps);
	}


}
