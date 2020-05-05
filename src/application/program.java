package application;

import java.util.Date;

import model.entities.Department;
import model.entities.Seller;

public class program {

	public static void main(String[] args) {
		
		Department depart = new Department(1, "books");		
		
		Seller seller = new Seller(10, "Daniel" , "daniel@hotmail.com", new Date(), 2500.0, depart);
		
		System.out.println(seller);

	}

}
