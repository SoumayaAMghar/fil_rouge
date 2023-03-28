import { Component } from '@angular/core';
import { Router, ActivatedRoute  } from '@angular/router';
import { RegisterRequest } from 'src/app/models/RegisterRequest';
import { RegisterResponse } from 'src/app/models/RegisterResponse';
import { AuthService } from 'src/app/services/auth/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  username = '';
  email = '';
  password = '';
  phone='';
  speciality='';
  picture='';

  constructor(private authService: AuthService, private router: Router, private route: ActivatedRoute) {}

  register() {
      const request = new RegisterRequest(this.username,this.email, this.password,this.phone,this.speciality,this.picture);
      this.authService.register(request).subscribe((response: RegisterResponse) => {
        // handle the token returned by the backend
        //localStorage.setItem('token', response.accessToken);
        this.router.navigate(['/login']);
      });
  } 
  goToLoginPage() {
    this.router.navigate(['/login']);
  } 
}
