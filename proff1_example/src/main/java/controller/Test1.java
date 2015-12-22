package controller;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import dao.ProductDao;
import dao.ProductDaoImpl;
import dao.UserDao;
import dao.UserDaoImpl;
import data.Product;
import data.User;
import service.ProductService;
import service.ProductServiceImpl;
import service.UserService;
import service.UserServiceImpl;
import util.HibernateUtil;

public class Test1 {
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		System.out.println("******  example 1  *******************");
		UserDao userDao = new UserDaoImpl();
		UserService servisUser = new UserServiceImpl(userDao);
		List<?> list = servisUser.getAllUsers();
		list.forEach(us -> System.out.println(us));
		//********************************************************
		
	}
}
