package model.entities;

import java.io.Serializable;

public class Department implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idDepart;
	private String nomeDepart;

	public Department() {

	}

	public Department(Integer idDepart, String nomeDepart) {
		this.idDepart = idDepart;
		this.nomeDepart = nomeDepart;
	}

	public Integer getIdDepart() {
		return idDepart;
	}

	public void setIdDepart(Integer idDepart) {
		this.idDepart = idDepart;
	}

	public String getNomeDepart() {
		return nomeDepart;
	}

	public void setNomeDepart(String nomeDepart) {
		this.nomeDepart = nomeDepart;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idDepart == null) ? 0 : idDepart.hashCode());
		result = prime * result + ((nomeDepart == null) ? 0 : nomeDepart.hashCode());
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
		Department other = (Department) obj;
		if (idDepart == null) {
			if (other.idDepart != null)
				return false;
		} else if (!idDepart.equals(other.idDepart))
			return false;
		if (nomeDepart == null) {
			if (other.nomeDepart != null)
				return false;
		} else if (!nomeDepart.equals(other.nomeDepart))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Department [idDepart=" + idDepart + ", nomeDepart=" + nomeDepart + "]";
	}

}
