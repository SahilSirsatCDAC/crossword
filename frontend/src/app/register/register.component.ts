import { AuthService } from './../services/auth.service';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  registerForm: FormGroup;

  constructor(private fb: FormBuilder, private authService :AuthService, private router: Router) {
    this.registerForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', Validators.required]
    }, {
      validators: this.passwordMatchValidator
    });
  }

  // Custom validator to check if password and confirm password match
  private passwordMatchValidator(form: FormGroup): void {
    const password = form.get('password');
    const confirmPassword = form.get('confirmPassword');

    if (password?.value !== confirmPassword?.value) {
      confirmPassword?.setErrors({ mismatch: true });
    } else {
      confirmPassword?.setErrors(null);
    }
  }

  onSubmit(): void {
    if (this.registerForm.valid) {
      // console.log(this.registerForm.value.email);
      // this.authService.registerUser(this.registerForm.value);
      console.log('Registration successful!');
      this.registerForm.value.roles = ["ROLE_USER"];
      console.log('Form submitted with data:', this.registerForm.value);
    this.authService.registerUser(this.registerForm.value).subscribe((data) => {
      console.log(data);
      // const decodedToken = this.jwtHelper.decodeToken(data.accessToken);
      // console.log(decodedToken);
      // localStorage.setItem("email", decodedToken.sub);
      // localStorage.setItem("userId", decodedToken.userId);
      // localStorage.setItem("userData", decodedToken);
      // this.triggerReinitialize();
      this.router.navigate(['/login']);
    });
    }
  }
}
