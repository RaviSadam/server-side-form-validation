package com.ravi.formValidation.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
	@Id
	@SequenceGenerator(
			name="userIdGenerator",
			sequenceName="userIdGenerator",
			allocationSize=1
	)
	@GeneratedValue(
			strategy=GenerationType.SEQUENCE,
			generator="userIdGenerator"
	)
	@Column(name="user_id")
	private long userId;
	
	@Column(name="name",length=30,nullable=false)
	private String name;
	
	@Column(name="email",unique = true,nullable = false)
	private String email;
	
	@Column(name="gender",length=10)
	private String gender;
	

	@Column(name="country_code")
	private short countryCode;
	
	@Column(name="phone_number")
	private String phone;
	
	@Column(length=30)
	private String village;
	
	@Column(length=30)
	private String mandal;
	
	@Column(length=30)
	private String district;

	@Column(nullable = false)
	private String password;
}
