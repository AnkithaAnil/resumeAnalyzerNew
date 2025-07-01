import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ResumeService {

  private apiUrl = 'http://localhost:8080/resume'; // 🔁 Change if needed

  constructor(private http: HttpClient) {}

  downloadEditedResume(userId: number): Observable<Blob> {
    const url = `${this.apiUrl}/download-edited?userId=${userId}`;
    return this.http.get(url, { responseType: 'blob' });
  }
}
