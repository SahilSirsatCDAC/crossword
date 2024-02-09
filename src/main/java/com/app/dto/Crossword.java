package com.app.dto;

import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
public class Crossword {
	@JsonProperty(access = Access.READ_ONLY)
	public long crosswordId;
	@NotBlank(message = "Crossword name cannot be empty")
	@Length(max = 50)
	private String crosswordName;
	@NotBlank(message = "Crossword category cannot be empty")
	@Length(max = 50)
	private String crosswordCategory;
	@NotBlank(message = "Size cannot be empty")
	@Min(value=2, message = "Minimum crossword size is 2 by 2")
    @Max(value=100, message = "Maximum crossword size is 100 by 100")
	private int crosswordSize;
	@NotBlank(message = "Difficulty cannot be empty")
	@Length(max = 50)
	private String crosswordDiffculty;
}
