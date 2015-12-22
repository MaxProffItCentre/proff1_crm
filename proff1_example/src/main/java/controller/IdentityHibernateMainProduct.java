package controller;

import dao.ProductDaoImpl;
import data.Product;
import util.HibernateUtil;

public class IdentityHibernateMainProduct {
	public static void main(String[] args) {
		ProductDaoImpl productImpl = new ProductDaoImpl();
		productImpl.findAll().forEach(pr->System.out.println(pr));
		
		Product product = productImpl.read(25l);
		System.out.println("product="+product);
		product.setBarcode(75);
		product.setId(30l);
		productImpl.update(product);
		
		productImpl.findAll().forEach(pr->System.out.println(pr));
		/*
		List<Product> products = ProductImpl.findAll();
		products.forEach(product->System.out.println(product.getName()));
		*/
		HibernateUtil.getSessionFactory().close();
	}
}
