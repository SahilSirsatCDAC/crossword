package com.app.entities;

import java.io.Serializable;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_progress", uniqueConstraints = { @UniqueConstraint(columnNames = { "user_id", "crossword_id", "word_id" }) })
@Getter
@Setter
//@IdClass(UserHistoryId.class)
public class UserProgressEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long userProgressId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private UserEntity user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "crossword_id")
	private CrosswordEntity crossWord;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "word_id")
	private WordEntity word;
	
	
}
