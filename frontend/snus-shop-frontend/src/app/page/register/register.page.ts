import { Component, OnInit } from '@angular/core';
import {IonicModule} from '@ionic/angular';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {RegisterRequest} from '../../model/RegisterRequest';
import {ToastService} from '../../services/toast/toast.service';
import {Router} from '@angular/router';
import {AuthService} from '../../services/auth/auth.service';
import {AuthResponse} from '../../model/AuthResponse';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-register',
  templateUrl: './register.page.html',
  styleUrls: ['./register.page.scss'],
  imports: [
    IonicModule,
    ReactiveFormsModule,
    NgIf
  ]
})
export class RegisterPage implements OnInit {
  registerForm: FormGroup;
  registerRequest: RegisterRequest;

  constructor(
    private router: Router,
    private fb: FormBuilder,
    private authService: AuthService,
    private toastService: ToastService
  ) {
    this.registerRequest = new RegisterRequest();
    this.registerForm = this.fb.group({
      firstName: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(30)]],
      lastName: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(30)]],
      address: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(30)]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(64)]],
    })
  }

  ngOnInit() {
  }

  redirectToLogin() {
    this.router.navigate(['login']);
  }

  registerUser() {
    this.registerRequest.email = this.registerForm.value.email;
    this.registerRequest.firstName = this.registerForm.value.firstName;
    this.registerRequest.lastName = this.registerForm.value.lastName;
    this.registerRequest.password = this.registerForm.value.password;
    this.authService.register(this.registerRequest).subscribe({
      next: (response: AuthResponse) => {
        localStorage.setItem('token', response.jwtToken)
        setTimeout(() => {
          this.router.navigate(['home']).then()
        }, 500)
      },
      error: err => {
        this.toastService.createToast('Registrierung fehlgeschlagen', 'danger', 2000);
      }
    })
  }

  control(value: 'firstName' | 'lastName' | 'email' | 'password' | 'confirmPassword') {
    return this.registerForm.get(value);
  }

  value(value: 'firstName' | 'lastName' | 'email' | 'password'): string {
    return this.registerForm.get(value)?.value;
  }

}
