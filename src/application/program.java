package application;

import java.util.List;

import model.dao.DaoFactury;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class program {

	public static void main(String[] args) {		
		
		SellerDao sellerDao = DaoFactury.createSellerDao();
		
		System.out.println("=== TESTE 1 : seller findById ====");
		Seller obj  = sellerDao.findById(3);
		System.out.println(obj);

		System.out.println("\n=== TESTE 2 : seller findByDepart ====");
		Department dep = new Department(2, null);
		List<Seller> obj2 = sellerDao.findByDepart(dep);
		for (Seller se : obj2) {
			System.out.println(se);
		}
		
	}

}
