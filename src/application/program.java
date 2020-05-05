package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import model.dao.DaoFactury;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class program {

	public static void main(String[] args) throws ParseException {		
		
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
		
		System.out.println("\n=== TESTE 3 : seller findAll ====");
	    List<Seller> obj3 = sellerDao.findAll();
		for (Seller se : obj3) {
			System.out.println(se);
		}
		
		System.out.println("\n=== TESTE 4 : seller insert ====");
		SimpleDateFormat  formato = new SimpleDateFormat("dd/MM/yyyy");
		Date data  = formato.parse("01/01/2020");
		Department dep2 = new Department(3, "Fashion");
		Seller obj4 = new Seller(null, "Flávia", "flavia@gmail.com", data, 2500.0, dep2);
		//sellerDao.insert(obj4);
		//System.out.println("Insert: " + obj4.getIdSeller() );
		
		System.out.println("\n=== TESTE 5 : seller update ====");
		Date datas  = formato.parse("01/01/1984");
		Department dep3 = new Department(3, null);
		Seller obj5 = new Seller(19, "Flávia", "flavia@gmail.com", datas, 2500.0, dep3);
		//sellerDao.update(obj5);
		//System.out.println("update: " + obj5.getIdSeller() );
		
		System.out.println("\n=== TESTE 6 : seller delete ====");		
		sellerDao.deleteById(9);
		System.out.println("delete: " + obj5.getIdSeller() );
		
	}

}
