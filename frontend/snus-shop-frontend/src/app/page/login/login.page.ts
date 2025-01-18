import { Component, OnInit } from '@angular/core';
import {IonicModule} from '@ionic/angular';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {LoginRequest} from '../../model/LoginRequest';
import {AuthService} from '../../services/auth/auth.service';
import {AuthResponse} from '../../model/AuthResponse';
import {Router} from '@angular/router';
import { ToastService } from 'src/app/services/toast/toast.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
  imports: [
    IonicModule,
    ReactiveFormsModule
  ]
})
export class LoginPage implements OnInit {
  loginForm: FormGroup;
  loginRequest: LoginRequest;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private toastService: ToastService
  ) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: [''],
    })
    this.loginRequest = new LoginRequest();
  }

  ngOnInit() {
  }

  loginUser(){
    this.loginRequest.email = this.loginForm.value.email;
    this.loginRequest.password = this.loginForm.value.password;
    this.authService.login(this.loginRequest).subscribe({
      next: (response: AuthResponse) => {
        localStorage.setItem('token', response.jwtToken)
        this.router.navigate(['home']).then()

      },
      error: err => {
        this.toastService.createToast('Falscher benutzername oder passwort', 'warning', 2000);
      }
    })
  }

  redirectToRegister() {
    this.router.navigate(['register']);
  }
}
