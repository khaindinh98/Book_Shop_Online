package com.example.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "persistent_logins")
@Data
@NoArgsConstructor
public class Persitence {
	
	@Column(name = "username")
	private String username;
	
	@Id
	@Column(name = "series")
	private String series;
	
	@Column(name = "token")
	private String token;
	
	@Column(name = "last_used")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUsed;
	
}
