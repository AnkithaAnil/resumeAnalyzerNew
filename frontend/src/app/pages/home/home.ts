import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [RouterModule, CommonModule],
  templateUrl: './home.html',
  styleUrls: ['./home.css']
})
export class HomeComponent implements OnInit {
  username: string = ''; // ✅ Declare the username property

 ngOnInit() {
  const user = JSON.parse(localStorage.getItem('user') || '{}');
  
  if (user?.email) {
    this.username = user.email.split('@')[0];  // 👈 Get part before '@'
  } else {
    this.username = 'Guest';
  }
}
}
