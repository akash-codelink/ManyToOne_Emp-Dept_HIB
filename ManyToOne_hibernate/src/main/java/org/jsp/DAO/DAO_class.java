package org.jsp.DAO;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jsp.DTO.Department;
import org.jsp.DTO.Employee;

public class DAO_class {
static Scanner sc=new Scanner(System.in);
static EntityManager m= Persistence.createEntityManagerFactory("dev").createEntityManager();
	
	public static void savedetails(Department d, Employee e,Employee e1) {
		
		//m.persist(d);
		m.persist(e);
		m.persist(e1);
		
		EntityTransaction t=m.getTransaction();
		t.begin();
		t.commit();
		System.out.println("Record saved");
	}
	
	public static void updatedept(Department d) {
		
		Department d1=m.find(Department.class, d.getId());
		
		if(d1!=null) {
			
		
			
			System.out.print("Enter the Department name");
			d1.setName(sc.next());
			System.out.print("Enter the Department location");
			d1.setLocation(sc.next());
			
			EntityTransaction t=m.getTransaction();
			t.begin();
			t.commit();
			System.out.println("record updated");
		}
	}
	public static List<Employee> fetchemployee(int id) {
		
		String jpql="select d.employee from Department d where d.id=?1";
		Query q=m.createQuery(jpql);
		
		q.setParameter(1,id);
		
		List<Employee> emps = q.getResultList();
		
		return emps;
		
		
		
	}
	public static void deletedept(int eid) {
		
		
		Department d1=m.find(Department.class, eid);
		
		if(d1!=null) {
			m.remove(d1);
			EntityTransaction t=m.getTransaction();
			
			t.begin();
			t.commit();
			System.out.println("deleted");
		}
		else {
			System.out.println("invalid Employee id");
	}
	
	
	}	
	public static Employee fetchempByIdAndemail(int id,String email) {
		
		String jpql="select e from Employee e where e.id=?1 and e.email=?2";
		
		Query q=m.createQuery(jpql);
		
		q.setParameter(1,id);
		q.setParameter(2, email);
		try {
			Employee e1=(Employee)q.getSingleResult();
			return e1;
			
			}
			catch(NoResultException e2) {
				
				return null;
			}
		
	}
	
	public static Employee empByPhoneAndEmail(String email,long phone) {
		
		String jpql="select e from Employee e where e.number=?1 and e.email=?2";
		
		Query q=m.createQuery(jpql);
		
		q.setParameter(1, phone);
		q.setParameter(2, email);
		
		try {
			Employee e1=(Employee)q.getSingleResult();
			return e1;
			
			}
			catch(NoResultException e2) {
				
				return null;
			}
		
		
	}
	public static List<Employee> empNames(Employee e) {
		String jpql="select e from Employee e ";
		
		Query q=m.createQuery(jpql);
		List<Employee> employee=q.getResultList();
		
		
			return employee;
		
	}
	public static List<Employee> empByIDandName(int deptid, String deptname) {
		
		String jpql="select e from Employee e where e.dept.id=?1 and e.dept.name=?2 ";
		
		Query q=m.createQuery(jpql);
		
		q.setParameter(1,deptid);
		
		q.setParameter(2, deptname);
		 
		
		List<Employee> e= q.getResultList();
		
			return e;
			
		
	
	
}
	
	
	
}
