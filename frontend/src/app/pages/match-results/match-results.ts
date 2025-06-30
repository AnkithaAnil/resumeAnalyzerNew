import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Router } from '@angular/router'; // ✅ Import Router

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

  constructor(private http: HttpClient, private router: Router) {} // ✅ Inject Router

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

  saveHistory(): void {
    const user = JSON.parse(localStorage.getItem('user') || '{}');

    if (!user?.email || !this.latestResult) {
      alert("User not logged in or no result found.");
      return;
    }

    const payload = {
      resumeFileName: this.latestResult.resumeFileName,
      jdTitle: this.latestResult.jdTitle,
      matchPercentage: this.latestResult.matchPercentage,
      userEmail: user.email
    };

    this.http.post('http://localhost:8080/api/history/save', payload).subscribe({
      next: () => {
        alert("✅ Match history saved!");
        this.router.navigate(['/dashboard']); // ✅ Navigate after save
      },
      error: (err) => {
        console.error("❌ Save failed", err);
        alert("Failed to save match history.");
      }
    });
  }
  downloadResult(): void {
  if (!this.latestResult) {
    alert('No result available to download.');
    return;
  }

  const user = JSON.parse(localStorage.getItem('user') || '{}');
  const content = `
    Resume: ${this.latestResult.resumeFileName}
    JD Title: ${this.latestResult.jdTitle}
    Match Percentage: ${this.latestResult.matchPercentage}%
    Matched Skills: ${this.latestResult.matchedSkills.join(', ')}
    Missing Skills: ${this.latestResult.missingSkills.join(', ')}
    Analyzed for: ${user.email || 'Unknown'}
  `;

  const blob = new Blob([content], { type: 'text/plain;charset=utf-8' });
  const url = window.URL.createObjectURL(blob);

  const a = document.createElement('a');
  a.href = url;
  a.download = `${this.latestResult.resumeFileName.replace(/\.[^/.]+$/, "")}-match-result.txt`;
  document.body.appendChild(a);
  a.click();
  document.body.removeChild(a);
}
}
