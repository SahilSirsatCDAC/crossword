package com.app.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class WordId implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long word_id;
	
	@Column(length = 50, nullable = false)
	private String word;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "crossword_id")
	private CrosswordEntity crossWord;
}
