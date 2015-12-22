package controller;

import java.util.List;

import dao.UserDaoImpl;
import data.Employee;
import data.User;

public class Test2 {
	public static void main(String[] args) {
		User user = new User();
		user.setName("Proktor");
		user.setLogin("user355");
		user.setPass("1");
		Employee employee = new Employee();
		employee.setName("Procter");
		employee.setSalary(200);
		user.setEmployee(employee);
		employee.setUser(user);
		//System.out.println(user);
		
		UserDaoImpl userDaoImpl = new UserDaoImpl();
		userDaoImpl.findAll().forEach(us->System.out.println(us));
		userDaoImpl.create(user);
		System.out.println();
		userDaoImpl.findAll().forEach(us->System.out.println(us));
		
	}
}
