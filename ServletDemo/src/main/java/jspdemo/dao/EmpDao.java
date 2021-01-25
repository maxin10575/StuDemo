package jspdemo.dao;


import jspdemo.entity.Emp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EmpDao implements Serializable {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 7643883579659743713L;

	public List<Emp> findAll(){
		List<Emp> list = new ArrayList<Emp>();
		Emp e1 = new Emp();
		e1.setEmpno(1);
		e1.setEname("��ɮ");
		e1.setJob("�쵼");
		e1.setSal(9000.0);
		list.add(e1);
		
		Emp e2 = new Emp();
		e2.setEmpno(2);
		e2.setEname("�����");
		e2.setJob("ͽ��");
		e2.setSal(5000.0);
		list.add(e2);
		
		Emp e3 = new Emp();
		e3.setEmpno(3);
		e3.setEname("��˽�");
		e3.setJob("ͽ��");
		e3.setSal(6000.0);
		list.add(e3);
		return list;
	}
	
	public void save(Emp e){
		System.out.println("����Ա��"+e);
	}
}
