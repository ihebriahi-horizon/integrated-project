import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth/services/auth-service.service';
import {
  AbstractControl,
  FormGroup,
  UntypedFormBuilder,
  UntypedFormGroup,
  Validators,
} from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
})
export class RegisterComponent {
  registerForm!: UntypedFormGroup;
  errorMessage!: string;

  constructor(
    private formBuilder: UntypedFormBuilder,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      userFirstname: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(30)]],
      userLastname: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(30)]],
      userGender: ['', [Validators.required]],
      userAge: ['', [Validators.required, Validators.min(7), Validators.maxLength(120)]],
      userAddress: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(50)]],
      userEmail: ['', [Validators.required, Validators.email]],
      userPassword: ['', [Validators.required, Validators.minLength(5)]],
      confirmPassword: ['', [Validators.required]],
    },
    {
      validator: CustomValidator.matches,
    });
  }

  onSubmit() {
    let { confirmPassword, ...registerFormValue } = this.registerForm.value;
    
    this.authService
      .register(registerFormValue)
      .subscribe(
        (response) => {
          if (response.status === 200) {
            this.router.navigate(['/login']);
          }
        },
        (error) => {
          if (error.status === 'UNAUTHORIZED') {
            this.errorMessage = 'Invalid email or password';
          } else {
            this.errorMessage = 'An error occurred while logging in';
          }
        }
      );
  }
  //convenience getter for easy access to form fields
  get form(): { [key: string]: AbstractControl } {
    return this.registerForm.controls;
  }
}

export class CustomValidator {
  static matches(group: FormGroup): { [key: string]: boolean } | null {
    const password = group.get('password')?.value;
    const confirmPassword = group.get('confirmPassword')?.value;

    return password === confirmPassword ? null : { 'passwordMismatch': true };
  }
}