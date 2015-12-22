package controller;

import dao.GenericDao;
import dao.GenericDaoImpl;
import data.Employee;

public class Action12Test1 {
	public static void main(String[] args) {
		GenericDao<Employee> employeeDao = new GenericDaoImpl<Employee>(Employee.class);
		employeeDao.findAll().forEach(empl->System.out.println(empl));
	}
}
