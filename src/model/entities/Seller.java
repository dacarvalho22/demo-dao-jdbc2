package model.entities;

import java.io.Serializable;
import java.util.Date;

public class Seller implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer idSeller;
	private String nameSeller;
	private String emailSelle;
	private Date birthDateSeller;
	private Double BaseSalarySeller;
	private Department department;

	public Seller() {

	}

	

	public Seller(Integer idSeller, String nameSeller, String emailSelle, Date birthDateSeller, Double baseSalarySeller,
			Department department) {
		this.idSeller = idSeller;
		this.nameSeller = nameSeller;
		this.emailSelle = emailSelle;
		this.birthDateSeller = birthDateSeller;
		BaseSalarySeller = baseSalarySeller;
		this.department = department;
	}

	public Integer getIdSeller() {
		return idSeller;
	}

	public void setIdSeller(Integer idSeller) {
		this.idSeller = idSeller;
	}

	public String getNameSeller() {
		return nameSeller;
	}

	public void setNameSeller(String nameSeller) {
		this.nameSeller = nameSeller;
	}

	public String getEmailSelle() {
		return emailSelle;
	}

	public void setEmailSelle(String emailSelle) {
		this.emailSelle = emailSelle;
	}

	public Date getBirthDateSeller() {
		return birthDateSeller;
	}

	public void setBirthDateSeller(Date birthDateSeller) {
		this.birthDateSeller = birthDateSeller;
	}

	public Double getBaseSalarySeller() {
		return BaseSalarySeller;
	}

	public void setBaseSalarySeller(Double baseSalarySeller) {
		BaseSalarySeller = baseSalarySeller;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((BaseSalarySeller == null) ? 0 : BaseSalarySeller.hashCode());
		result = prime * result + ((birthDateSeller == null) ? 0 : birthDateSeller.hashCode());
		result = prime * result + ((department == null) ? 0 : department.hashCode());
		result = prime * result + ((emailSelle == null) ? 0 : emailSelle.hashCode());
		result = prime * result + ((idSeller == null) ? 0 : idSeller.hashCode());
		result = prime * result + ((nameSeller == null) ? 0 : nameSeller.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seller other = (Seller) obj;
		if (BaseSalarySeller == null) {
			if (other.BaseSalarySeller != null)
				return false;
		} else if (!BaseSalarySeller.equals(other.BaseSalarySeller))
			return false;
		if (birthDateSeller == null) {
			if (other.birthDateSeller != null)
				return false;
		} else if (!birthDateSeller.equals(other.birthDateSeller))
			return false;
		if (department == null) {
			if (other.department != null)
				return false;
		} else if (!department.equals(other.department))
			return false;
		if (emailSelle == null) {
			if (other.emailSelle != null)
				return false;
		} else if (!emailSelle.equals(other.emailSelle))
			return false;
		if (idSeller == null) {
			if (other.idSeller != null)
				return false;
		} else if (!idSeller.equals(other.idSeller))
			return false;
		if (nameSeller == null) {
			if (other.nameSeller != null)
				return false;
		} else if (!nameSeller.equals(other.nameSeller))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "seller [idSeller=" + idSeller + ", nameSeller=" + nameSeller + ", emailSelle=" + emailSelle
				+ ", birthDateSeller=" + birthDateSeller + ", BaseSalarySeller=" + BaseSalarySeller + ", department="
				+ department + "]";
	}

}
