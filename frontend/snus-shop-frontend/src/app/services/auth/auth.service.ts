import { Injectable } from '@angular/core';
import {LoginRequest} from '../../model/LoginRequest';
import {HttpClient} from '@angular/common/http';
import {RegisterRequest} from '../../model/RegisterRequest';
import {AuthResponse} from '../../model/AuthResponse';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  authURL: string = 'http://localhost:8222/snus-shop'

  constructor(
    private http: HttpClient
  ) { }

  register(registerRequest: RegisterRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(this.authURL + "/register", registerRequest);
  }

  login(loginRequest: LoginRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(this.authURL + "/login", loginRequest);
  }
}
