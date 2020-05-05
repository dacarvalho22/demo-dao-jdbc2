package model.dao;

import model.dao.impl.SellerDaoJDBC;

public class DaoFactury {

	public static SellerDao createSellerDao() {
		return new SellerDaoJDBC();
	}
}
