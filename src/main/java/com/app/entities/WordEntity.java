package com.app.entities;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "word", uniqueConstraints = { @UniqueConstraint(columnNames = { "word", "crossword_id" }) })
@Getter
@Setter
//@IdClass(WordId.class)
public class WordEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long word_id;
	
	@Column(length = 50, nullable = false)
	private String orientation;//TODO create enum, for now we will pass either V or H
	
	@Column(length = 200, nullable = false)
	private String hint;

	@Column(nullable = false)
	private int startRow;
	
	@Column(nullable = false)
	private int startColumn;
	
	@Column(nullable = false)
	private int wordLength;
	
	@Column(length = 50, nullable = false)
	private String word;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "crossword_id")
	private CrosswordEntity crossword;
	
	
}
