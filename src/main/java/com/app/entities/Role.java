package com.app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

import com.app.dto.UserRole;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "roles")
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Role extends BaseEntity {

	@Enumerated(EnumType.STRING)
	@Column(length = 20,unique = true)
	private UserRole roleName;

	public Role(UserRole roleName) {
		super();
		this.roleName = roleName;
	}
	
}
