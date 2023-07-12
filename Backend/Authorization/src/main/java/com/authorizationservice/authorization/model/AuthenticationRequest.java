package com.authorizationservice.authorization.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="login")
public class AuthenticationRequest {
	@Id
	@Column(name="username")
	public String username;
	
	@Column(name="password")
	public String password;
}
