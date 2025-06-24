import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';

interface MatchResult {
  resumeFileName: string;
  jdTitle: string;
  matchPercentage: number;
  missingSkills: string[];
  matchedSkills: string[];
}



@Component({
  selector: 'app-match-results',
  standalone: true,
  imports: [CommonModule, HttpClientModule],
  templateUrl: './match-results.html',
  styleUrls: ['./match-results.css']
})
export class MatchResults implements OnInit {
  latestResult: MatchResult | null = null;
  error: string = '';
  loading = true;

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.http.get<MatchResult[]>('http://localhost:8080/api/match/results').subscribe({
      next: (response) => {
        this.latestResult = response?.[response.length - 1] || null;
        this.loading = false;
      },
      error: (err) => {
        console.error('❌ Failed to fetch results:', err);
        this.error = 'Failed to load match results.';
        this.loading = false;
      }
      
    });
  }
}
