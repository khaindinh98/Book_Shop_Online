package com.example.demo.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldMatch.List({
    @FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match"),
    @FieldMatch(first = "email", second = "confirmEmail", message = "The email fields must match")
})
public class UserRegistrationDto {
	
	@NotEmpty
	private String firstName;

	@NotEmpty
	private String lastName;
	
	@NotEmpty
	private String userName;

	@NotEmpty
	@Pattern(regexp="(^$|[0-9]{10})")
	private String phone;

	@NotEmpty
	private String password;

	@NotEmpty
	private String confirmPassword;

	@Email
	@NotEmpty
	private String email;

	@Email
	@NotEmpty
	private String confirmEmail;
	
	
	
	
	

}
