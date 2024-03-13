package org.jsp.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.jsp.DAO.DAO_class;
import org.jsp.DTO.Department;
import org.jsp.DTO.Employee;

public class User_details {
	static Scanner sc=new Scanner(System.in);
	static DAO_class dao=new DAO_class();
	
	public static void main(String[] args) {
		
		System.out.println("------------------Enter your choice ---------------------");
	System.out.println();
		System.out.println("1. Save department and Employee details");
		System.out.println("2. update department  details");
		System.out.println("3. Fetch Employee by Department id");
		System.out.println("4. delete department");
		System.out.println("5. Fetch Employee by employee id and Employee email");
		System.out.println("6. Fetch Employee by Phone and email");
		System.out.println("7. display all the employee names");
		System.out.println("8. Employee details by Department id and name");
		System.out.println();
		System.out.println("---------------------------------------");
		
		System.out.print("Enter your choice :");
		int choice=sc.nextInt();
		
		switch(choice) {
		case 1:
			save();
			break;
		case 2:
			update();
			break;
			
		case 3:
			fetchEmp();
			break;
		case 4:
			delete();
			break;
		case 5:
			employ();
			break;
		case 6: 
			emp_phone();
			break;
		case 7:
			EmpName();
			break;
		case 8:
			empdetByIdandName();
			break;
			default :
				System.out.println("Invalid choice");
		}
		
	
	}
	
	public static void save() {
		Department d=new Department();
		System.out.println("Enter the department details");
		System.out.println();
		System.out.print("Enter the Department name :");
		d.setName(sc.next());
		System.out.print("Enter the Department location :");
		d.setLocation(sc.next());
		
		
				Employee e= new Employee();
				System.out.println("------------------------------------");
				System.out.println("Enter the Employee Details  1");
				System.out.println();
				System.out.print("Enter the Employee name :");
				e.setEmployee_name(sc.next());
				System.out.print("Enter the Employee salary :");
				e.setSalary(sc.nextDouble());
				System.out.print("Enter the Employee Email :");
				e.setEmail(sc.next());
				System.out.print("Enter the Employee Password :");
				e.setPassword(sc.next());
				System.out.print("Enter the Employee phone number :");
				e.setNumber(sc.nextLong());
				e.setDept(d);
			
			
		
		
		
		
		Employee e1= new Employee();
		System.out.println("------------------------------------");
		System.out.println("Enter the Employee Details  2");
		System.out.println();
		System.out.print("Enter the Employee name :");
		e1.setEmployee_name(sc.next());
		System.out.print("Enter the Employee salary :");
		e1.setSalary(sc.nextDouble());
		System.out.print("Enter the Employee Email :");
		e1.setEmail(sc.next());
		System.out.print("Enter the Employee Password :");
		e1.setPassword(sc.next());
		System.out.print("Enter the Employee phone number :");
		e1.setNumber(sc.nextLong());
		e1.setDept(d);
	
		d.setEmployee(new ArrayList<Employee>(Arrays.asList(e,e1)));
		dao.savedetails(d,e,e1);
		
		
	}
	
	
	public static void update() {
		Department d=new Department();
		System.out.print("Enter the department id :");
		d.setId(sc.nextInt());
		
		dao.updatedept(d);
		
		
	}
	
	public static void fetchEmp() {
		
		System.out.print("Enter the department id :");
	int deptid=sc.nextInt();
		
	List<Employee> e=dao.fetchemployee(deptid);
	if(e!=null) {
		
		for(Employee e1:e) {
	    System.out.println();
        System.out.println("---------EMPLOYEE DETAILS---------- ");
        System.out.println("Employee id :"+e1.getEmp_id());
		System.out.println("Employee name :"+e1.getEmployee_name());
		System.out.println("Employee number :"+e1.getNumber());
		System.out.println("Employee password :"+e1.getPassword());
		System.out.println("Emloyee salary :"+e1.getSalary());
		System.out.println();
		System.out.println("----------------------------------------------");
		}
	}
	else {
		System.out.println("Invalid Department id");
	}
		
		
		
	}
	public static void delete() {
		//Department d=new Department();
		System.out.print("enter the dept id to delete :");
		int deptid=sc.nextInt();
		dao.deletedept(deptid);
		
	}
	
	public static void employ() {
		
		Employee e= new Employee();
		System.out.println("Enter the Employee Details");
		System.out.print("enter the employee id :");
		int eid=sc.nextInt();
		System.out.print("Enter the Employee Email :");
		String email=sc.next();
		
		Employee e1=dao.fetchempByIdAndemail(eid,email);
		
		if(e1!=null) {
			System.out.println();
            System.out.println("---------EMPLOYEE DETAILS---------- ");
            System.out.println();
			System.out.println("Employee name : "+e1.getEmployee_name());
			System.out.println("Employee salary: "+e1.getSalary());
			System.out.println("Employee email :"+e1.getEmail());
			System.out.println("Employee password :"+e1.getPassword());
			System.out.println("Employee phone :"+e1.getNumber());
		}
		else {
			System.out.println("Invalid id or email");
		}
	}
	
	public static void emp_phone() {
		
		Employee e= new Employee();
		System.out.print("Enter the Employee Email :");
		String email=sc.next();
		System.out.print("Enter the Employee phone number :");
		long phone=sc.nextLong();
	
		
	Employee em=dao.empByPhoneAndEmail(email,phone);
	
	if(em!=null) {
		   System.out.println();

		    System.out.println("--------Employee Details ----------");
	        System.out.println();
		    System.out.println("Employee ID :"+em.getEmp_id());
			System.out.println("Employee name : "+em.getEmployee_name());
			System.out.println("Employee salary: "+em.getSalary());
			System.out.println("Employee email :"+em.getEmail());
			System.out.println("Employee password :"+em.getPassword());
			System.out.println("Employee phone :"+em.getNumber());
		
			}
	
	}
	
	public static void EmpName() {
		Employee e= new Employee();
		
	List<Employee>e1=	dao.empNames(e);
	if(e1!=null) {
		System.out.println("------Employee's Names ----------");
		for(Employee e2:e1) {
			
			System.out.println();
			System.out.println("Employee name :"+e2.getEmployee_name());
		}
	}
	}
	
	public static void empdetByIdandName() {
		Department d=new Department();
		System.out.print("Enter the Department id :");
		int deptid=sc.nextInt();
		System.out.print("Enter the Department Name :");
		String Deptname=sc.next();
		
List<Employee> e1=	dao.empByIDandName(deptid,Deptname);
if(e1!=null) {
	for(Employee e2:e1) {
		System.out.println();
		System.out.println("--------- Employee Details ---------");
		System.out.println();
		System.out.println("Employee ID :"+e2.getEmp_id());
		System.out.println("Employee name : "+e2.getEmployee_name());
		System.out.println("Employee salary: "+e2.getSalary());
		System.out.println("Employee email :"+e2.getEmail());
		System.out.println("Employee password :"+e2.getPassword());
		System.out.println("Employee phone :"+e2.getNumber());
	}
}
	
	
	
	}
}
