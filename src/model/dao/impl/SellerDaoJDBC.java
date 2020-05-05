package model.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {

	private Connection conn = null;
	Seller obj = null;
	Department dep = null;

	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Seller obj) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			conn = DB.getConnetion();
			String sql = "INSERT INTO seller "  
					+ " (Name, Email, BirthDate, BaseSalary, DepartmentId) " 
					+ " VALUES "  
					+ " (?, ?, ?, ?, ?) ";
			st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getNameSeller());
			st.setString(2, obj.getEmailSelle());
			st.setDate(3, new java.sql.Date((obj.getBirthDateSeller()).getTime()));
			st.setDouble(4, obj.getBaseSalarySeller());
			st.setInt(5, obj.getDepartment().getIdDepart());
			int rows = st.executeUpdate();
			if(rows > 0){		
				rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setIdSeller(id);
					System.out.println("Dados inseridos com sucesso. " + id);
				}				
			}else {
				throw new DbException("Erro ao inserir dados na tabela. ");
			}			
		} catch (SQLException e) {
			throw new DbException("Erro ao busca id do seller. " + e.getMessage());
		} finally {
			DB.closeStatement(st, rs);
		}
	}

	@Override
	public void update(Seller obj) {		
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			conn = DB.getConnetion();
			String sql = "UPDATE seller " 
					+ " SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? "  
					+ " WHERE Id = ? ";			
			st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getNameSeller());
			st.setString(2, obj.getEmailSelle());
			st.setDate(3, new java.sql.Date(obj.getBirthDateSeller().getTime()));
			st.setDouble(4, obj.getBaseSalarySeller());
			st.setInt(5, obj.getDepartment().getIdDepart());
			st.setInt(6, obj.getIdSeller());
			
			int rows = st.executeUpdate();
			if(rows > 0) {
				rs = st.getGeneratedKeys();
				if(rs.next()) {
					int idBd = rs.getInt(1);
					System.out.println("Dados atualizados com sucesso: " + idBd);
				}
			}else {
				throw new DbException("Erro ao atualizar dados. ");
			}		
		} catch (SQLException e) {
			throw new DbException("Erro ao busca id do seller. " + e.getMessage());
		} finally {
			DB.closeStatement(st, rs);
		}
	}

	@Override
	public void deleteById(Integer id) {
		
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
				dep = instantiateDepartment(rs);
				obj = instantiateSeller(rs, dep);
				return obj;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException("Erro ao busca id do seller. " + e.getMessage());
		} finally {
			DB.closeStatement(st, rs);
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
	public List<Seller> findAll() {
		List<Seller> lista = new ArrayList<Seller>();
		PreparedStatement st = null;
		ResultSet rs = null;		
		try{
			conn = DB.getConnetion();
			String sql = "SELECT seller.*,department.Name as DepName "
					+ " FROM seller INNER JOIN department "  
					+ " ON seller.DepartmentId = department.Id "  
					+ " ORDER BY Name";
			st  = conn.prepareStatement(sql);			
			rs = st.executeQuery();
			while(rs.next()) {
				dep = instantiateDepartment(rs);
				obj = instantiateSeller(rs, dep);
				lista.add(obj);
			}
			return lista;
		}catch(SQLException e){
			throw new DbException("Erro ao buscar dados no banco de dados. " + e.getMessage());
		}finally {
			DB.closeStatement(st, rs);
		}				
	}

	@Override
	public List<Seller> findByDepart(Department dep) {		
		Map<Integer, Department> listaMap = new HashMap<Integer, Department>();
		List<Seller> lista = new ArrayList<Seller>();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			conn = DB.getConnetion();
			String sql = "SELECT seller.*,department.Name as DepName " 
					+ " FROM seller INNER JOIN department " 
					+ " ON seller.DepartmentId = department.Id "  
					+ " WHERE DepartmentId = ? " 
					+ " ORDER BY Name ";
			st = conn.prepareStatement(sql);
			st.setInt(1, dep.getIdDepart()); // 2
			rs = st.executeQuery();	
			while(rs.next()) {	
			   	dep = listaMap.get(rs.getInt("DepartmentId"));
				if(dep == null) {
					dep = instantiateDepartment(rs);
					listaMap.put(rs.getInt("DepartmentId"), dep);
				}				
				obj = instantiateSeller(rs, dep);
				lista.add(obj);		 
			}					
			return lista;
		}catch(SQLException e) {
			throw new DbException("Erro ao buscar dados no banco de dados. " + e.getMessage());
		}finally {
			DB.closeStatement(st, rs);
		}	
		
	}

}
