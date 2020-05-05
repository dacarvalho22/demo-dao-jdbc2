package application;

import model.dao.DaoFactury;
import model.dao.SellerDao;
import model.entities.Seller;

public class program {

	public static void main(String[] args) {
		
		
		SellerDao sellerDao = DaoFactury.createSellerDao();
		
		System.out.println("=== TESTE 1 : seller findById ====");
		Seller obj  = sellerDao.findById(3);
		System.out.println(obj);
		
	

	}

}
