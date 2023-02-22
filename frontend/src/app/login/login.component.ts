import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoginRequest } from '../models/LoginRequest';
import { LoginResponse } from '../models/LoginResponse';
import { AuthService } from '../services/auth/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  email = '';
  password = '';

  constructor(private authService: AuthService, private router: Router) {}

  login() {
      const request = new LoginRequest(this.email, this.password);
      this.authService.login(request).subscribe((response: LoginResponse) => {
        // handle the token returned by the backend
        localStorage.setItem('token', response.accessToken);
        this.router.navigate(['/dashboard']);
      });
  }  
}
