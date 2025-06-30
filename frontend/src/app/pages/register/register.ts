import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-register',
  standalone: true,
  templateUrl: './register.html',
  styleUrls: ['./register.css'],
  imports: [FormsModule, CommonModule]
})
export class RegisterComponent {
  email = '';
  password = '';
  error = '';

  constructor(private http: HttpClient, private router: Router) {}

 register() {
  this.http.post('http://localhost:8080/api/auth/register', {
    email: this.email,
    password: this.password
  }, { responseType: 'text' }).subscribe({
    next: (res: string) => {
      alert(res); // shows "Registration successful"
      this.router.navigate(['/login']);
    },
    error: err => {
      console.error('Error during registration:', err);
      this.error = err?.error || 'Registration failed';
    }
  });
}


}
