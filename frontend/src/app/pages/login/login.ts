import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { NgIf } from '@angular/common';
import { RouterModule } from '@angular/router'; // ✅ Required for routerLink to work

@Component({
  selector: 'app-login',
  standalone: true,
  templateUrl: './login.html',
  styleUrls: ['./login.css'],
  imports: [FormsModule, NgIf, RouterModule] // ✅ Added RouterModule here
})
export class Login {
  email = '';
  password = '';
  error = '';

  constructor(private http: HttpClient, private router: Router) {}

  login() {
  this.http.post('http://localhost:8080/api/auth/login', {
    email: this.email,
    password: this.password
  }).subscribe({
    next: (res: any) => {
      localStorage.setItem('loggedIn', 'true');
      localStorage.setItem('user', JSON.stringify(res)); // Save user ID and email
      this.router.navigate(['/home']);
    },
    error: () => {
      this.error = 'Invalid email or password';
    }
  });
}
}
