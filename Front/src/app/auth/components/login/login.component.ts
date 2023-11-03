import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth-service.service';
import { AbstractControl, UntypedFormBuilder, UntypedFormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})

export class LoginComponent {
  loginForm!: UntypedFormGroup;
  errorMessage!: string;


  constructor(private formBuilder: UntypedFormBuilder, private authService: AuthService, private router: Router) { }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(5)]]
    });
  }

  onSubmit() {
    
    const loginFormValue: LoginForm = this.loginForm.value;
    this.authService.login(loginFormValue.email, loginFormValue.password).subscribe(
      response => {
        if (response.status === 200) {
          this.router.navigate(['/admin']);
        }
      },
      error => {
        if (error.status === 'UNAUTHORIZED') {
          this.errorMessage = 'Invalid email or password';
        } else {
          this.errorMessage = 'An error occurred while logging in';
        }
      }
    );
  }
  //convenience getter for easy access to form fields
  get form(): { [key: string]: AbstractControl; } {
    return this.loginForm.controls;
  }
}

interface LoginForm {
  email: string;
  password: string;
}