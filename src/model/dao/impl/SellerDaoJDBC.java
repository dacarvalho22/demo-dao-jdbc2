package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {

	private SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	private Connection conn = DB.getConnetion();
	Seller seller = null;
	Department dept = null;

	public SellerDaoJDBC(Connection conn) {
	 this.conn = conn;
	}

	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			conn = DB.getConnetion();
			String sql = "SELECT seller.*,department.Name as DepName " + " FROM seller INNER JOIN department "
					+ " ON seller.DepartmentId = department.Id " + " WHERE seller.Id = ? ";
			st = conn.prepareStatement(sql, Statement.KEEP_CURRENT_RESULT);
			st.setInt(1, id); // 2
			rs = st.executeQuery();
			if (rs.next()) {
				dept = instantiateDepartment(rs);
				seller = instantiateSeller(rs, dept);
				return seller;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException("Erro ao busca id do seller. " + e.getMessage());
		} finally {
			DB.closeStatement(st, rs);
			// DB.closeConnection();
		}
	}

	private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
		Seller obj = new Seller();
		obj.setIdSeller(rs.getInt("Id"));
		obj.setNameSeller(rs.getString("Name"));
		obj.setEmailSelle(rs.getString("Email"));
		obj.setBaseSalarySeller(rs.getDouble("BaseSalary"));
		obj.setBirthDateSeller(rs.getDate("BirthDate"));
		obj.setDepartment(dep);
		return obj;
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setIdDepart(rs.getInt("DepartmentId"));
		dep.setNomeDepart(rs.getString("DepName"));
		return dep;
	}

	@Override
	public List<Seller> findAll(Seller obj) {
		// TODO Auto-generated method stub
		return null;
	}

}
