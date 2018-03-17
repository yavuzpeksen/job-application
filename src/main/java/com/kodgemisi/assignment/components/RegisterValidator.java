package com.kodgemisi.assignment.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.kodgemisi.assignment.domains.form.RegisterForm;

@Component
public class RegisterValidator implements Validator{

	/*
  @Autowired
  private UserService userService;*/

  @Override
  public boolean supports(Class<?> aClass) {
      return RegisterForm.class.equals(aClass);
  }

  @Override
  public void validate(Object o, Errors errors) {
      RegisterForm registerForm = (RegisterForm) o;

      ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
      if (registerForm.getEmail().length() < 6 || registerForm.getEmail().length() > 16) {
          errors.rejectValue("email", "Size.userForm.email");
      }
      /*
      if (userService.findByUsername(user.getUsername()) != null) {
          errors.rejectValue("username", "Duplicate.userForm.username");
      }*/
		
      ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
      if (registerForm.getPassword().length() < 8 || registerForm.getPassword().length() > 32) {
          errors.rejectValue("password", "Size.userForm.password");
      }

      if (!registerForm.getPasswordConfirm().equals(registerForm.getPassword())) {
          errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
      }
  }
}
