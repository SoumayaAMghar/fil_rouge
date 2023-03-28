import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginRequest } from 'src/app/models/LoginRequest';
import { LoginResponse } from 'src/app/models/LoginResponse';
import { Router } from '@angular/router';
import { RegisterRequest } from 'src/app/models/RegisterRequest';
import { RegisterResponse } from 'src/app/models/RegisterResponse';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiServerUrl = 'http://localhost:8080';

  constructor(private http: HttpClient,private router: Router ) { }

  login(request: LoginRequest): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(`${this.apiServerUrl}/api/auth/login`, request);
  }
  register(request: RegisterRequest): Observable<RegisterResponse> {
    return this.http.post<RegisterResponse>(`${this.apiServerUrl}/api/auth/register`, request);
  }
  
  logout() {
    // Remove the JWT from local storage
    localStorage.removeItem('token');
    // Redirect to the login page
    this.router.navigate(['/']);
  }
}
