package com.app.dto;

import com.app.entities.CrosswordEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import jakarta.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Word {
	@JsonProperty(access = Access.READ_ONLY)
	public long word_id;

	@NotNull(message = "Room rent is required")
	private String orientation;// TODO create enum, for now we will pass either V or H

	@NotNull(message = "Room rent is required")
	private String hint;

	@NotNull(message = "Room rent is required")
	private int startRow;

	@NotNull(message = "Room rent is required")
	private int startColumn;

	@NotNull(message = "Room rent is required")
	private int wordLength;

	@NotNull(message = "Room rent is required")
	private String word;

	private Crossword crossword;
}
