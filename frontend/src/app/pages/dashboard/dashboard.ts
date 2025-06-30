import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  templateUrl: './dashboard.html',
  styleUrls: ['./dashboard.css'],
  imports: [CommonModule]
})
export class DashboardComponent implements OnInit {
  history: any[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    const user = JSON.parse(localStorage.getItem('user') || '{}');

    if (user?.id) {
      this.http.get<any[]>(`http://localhost:8080/api/history/${user.id}`).subscribe({
        next: (res) => {
          console.log("Match History:", res);
          this.history = res;
        },
        error: (err) => {
          console.error("Error fetching match history:", err);
        }
      });
    } else {
      console.warn("User not found in localStorage");
    }
  }
}
