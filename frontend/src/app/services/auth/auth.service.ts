import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginRequest } from 'src/app/models/LoginRequest';
import { LoginResponse } from 'src/app/models/LoginResponse';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiServerUrl = 'http://localhost:7070';

  constructor(private http: HttpClient,private router: Router ) { }

  login(request: LoginRequest): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(`${this.apiServerUrl}/api/auth/login`, request);
  }
  
  logout() {
    // Remove the JWT from local storage
    localStorage.removeItem('token');
    // Redirect to the login page
    this.router.navigate(['/']);
  }
}
