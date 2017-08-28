package com.testezup.xyinc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author Rodrigo Siqueira
 *
 */
@Entity
@Table(name="tb_poi")
public class Poi implements Serializable {

	private static final long serialVersionUID = 201708261639L;
	
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "Nome é obrigatório")
	@Size(max = 100, message = "O nome não pode conter mais de 100 caracteres")
	@Column(name = "name", nullable = false)
	private String name;

	@NotNull(message = "Coordenada X é obrigatória")
	@Min(value=0, message="O valor deve ser inteiro não negativo.")
	@Column(name = "coordinateX", nullable = false)
	private Long coordinateX;

	@NotNull(message = "Coordenada Y é obrigatória")
	@Min(value=0, message="O valor deve ser inteiro não negativo.")
	@Column(name = "coordinateY", nullable = false)
	private Long coordinateY;
	
	public Poi() {}
	public Poi(String name, Long coordinateX, Long coordinateY) {
		this.name = name;
		this.coordinateX = coordinateX;
		this.coordinateY = coordinateY;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getname() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}

	public Long getCoordinateX() {
		return coordinateX;
	}

	public void setCoordinateX(Long coordinateX) {
		this.coordinateX = coordinateX;
	}

	public Long getCoordinateY() {
		return coordinateY;
	}

	public void setCoordinateY(Long coordinateY) {
		this.coordinateY = coordinateY;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coordinateX == null) ? 0 : coordinateX.hashCode());
		result = prime * result + ((coordinateY == null) ? 0 : coordinateY.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Poi other = (Poi) obj;
		if (coordinateX == null) {
			if (other.coordinateX != null)
				return false;
		} else if (!coordinateX.equals(other.coordinateX))
			return false;
		if (coordinateY == null) {
			if (other.coordinateY != null)
				return false;
		} else if (!coordinateY.equals(other.coordinateY))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}	
	
	

}
