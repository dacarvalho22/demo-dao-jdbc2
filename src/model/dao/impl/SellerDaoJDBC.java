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
	private Connection conn;
	Seller seller = null;
	Department department = null;
	
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
		try{
			conn = DB.getConnetion();
			String sql = "SELECT seller.*,department.Name as DepName " 
					+ " FROM seller INNER JOIN department " 
					+ " ON seller.DepartmentId = department.Id "  
					+ " WHERE seller.Id = ? ";
			st = conn.prepareStatement(sql, Statement.KEEP_CURRENT_RESULT);
			st.setInt(1, id); //2		
			rs = st.executeQuery();
			if(rs.next()) {
				int IdDepart            = rs.getInt("DepartmentId");
				String nomeDepart       = rs.getString("DepName");						
				department = new Department(IdDepart, nomeDepart);
				
				int idSeller            = rs.getInt("Id");
				String nameSeller       = rs.getString("Name");
				String emailSelle       = rs.getString("Email");
				Date birthDateSeller    = rs.getDate("BirthDate");
				double baseSalarySeller = rs.getDouble("BaseSalary");			
				seller = new Seller(idSeller, nameSeller, emailSelle, birthDateSeller, baseSalarySeller, department);
				
				return seller;
			}
			return null;			
		}catch(SQLException e) {
			throw new DbException("Erro ao busca id do seller. " + e.getMessage());
		}finally {
			DB.closeStatement(st, rs);
			//DB.closeConnection();
		}		
	}

	@Override
	public List<Seller> findAll(Seller obj) {
		// TODO Auto-generated method stub
		return null;
	}

}
