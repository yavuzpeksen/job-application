package com.kodgemisi.assignment.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.kodgemisi.assignment.domains.form.RegisterForm;
import com.kodgemisi.assignment.domains.form.ResetPasswordForm;
import com.kodgemisi.assignment.services.interfaces.UserService;

@Component
public class ResetPasswordValidator implements Validator{

  @Autowired
  private UserService userService;

  @Override
  public boolean supports(Class<?> aClass) {
      return ResetPasswordForm.class.equals(aClass);
  }

  @Override
  public void validate(Object o, Errors errors) {
  		ResetPasswordForm resetForm = (ResetPasswordForm) o;

      ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
      if (resetForm.getPassword().length() < 8 || resetForm.getPassword().length() > 16) {
          errors.rejectValue("password", "Size.userForm.password");
      }

      if (!resetForm.getPasswordConfirm().equals(resetForm.getPassword())) {
          errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
      }
  }
  
}
