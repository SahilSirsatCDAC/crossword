package com.app.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "crossword")
@Getter
@Setter
public class CrosswordEntity {
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = super.hashCode();
//		result = prime * result + Objects.hash(category, diffculty, name, size);
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (!super.equals(obj))
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		CrossWordEntity other = (CrossWordEntity) obj;
//		return Objects.equals(category, other.category) && Objects.equals(diffculty, other.diffculty)
//				&& Objects.equals(name, other.name) && size == other.size;
//	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long crosswordId;
	
	@Column(length = 50, unique = true)
	private String crosswordName;
	
	@Column(length = 50)
	private String crosswordCategory;// TODO: make into enum
	
	@Column(name = "crossword_size")
	private int crosswordSize;
	
	@Column(length = 50)
	private String crosswordDiffculty;// TODO: make into enum
	
}
