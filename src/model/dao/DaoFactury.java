package model.dao;

import db.DB;
import model.dao.impl.DepartmenteDaoJDBC;
import model.dao.impl.SellerDaoJDBC;

public class DaoFactury {

	public static SellerDao createSellerDao() {
		return new SellerDaoJDBC(DB.getConnetion());
	}

	public static DepartmenteDao createDepartDao() {
		return new DepartmenteDaoJDBC();
	}

}
