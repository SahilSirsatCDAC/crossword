package com.app.dto;

import java.util.Set;

import org.hibernate.validator.constraints.Length;

import com.app.entities.CrosswordEntity;
import com.app.entities.UserEntity;
import com.app.entities.WordEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserProgress {
	@JsonProperty(access = Access.READ_ONLY)
	private long userProgressId;
	
	private User user;
	private Crossword crossword;
	private Word word;
}
