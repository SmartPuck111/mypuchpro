package com.blog.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class Login {

	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Email
	@NotNull(message = "Email format is require")
	private String email;

	@NotNull(message = "Password is require")
	@Size(min = 6, max = 16, message = "Password should between 6 to 16")
	private String password;

	@JsonIgnore
	private LocalDateTime date;
	
	@JsonIgnore
	private String loginKey;

}
