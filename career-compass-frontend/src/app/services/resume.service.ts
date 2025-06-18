import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class ResumeService {
  private BASE_URL = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  uploadResume(file: File): Observable<any> {
    const formData = new FormData();
    formData.append('file', file);
    return this.http.post(`${this.BASE_URL}/resume/upload`, formData);
  }

  uploadJD(jdData: any): Observable<any> {
    return this.http.post(`${this.BASE_URL}/jd/upload`, jdData);
  }

  getMatchResults(): Observable<any[]> {
    return this.http.get<any[]>(`${this.BASE_URL}/match/results`);
  }
}
