import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-upload',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule],
  templateUrl: './upload.html',
  styleUrls: ['./upload.css']
})
export class Upload {
  selectedFile: File | null = null;
  jobTitle: string = '';
  jobContent: string = '';

  uploadSuccess = false;     // ✅ resume success flag
  jdUploadSuccess = false;   // ✅ JD success flag

  resumeUploaded = false;
  jdUploaded = false;

  constructor(private router: Router, private http: HttpClient) {}

  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    this.selectedFile = input.files?.[0] ?? null;
  }

  onUpload(): void {
    if (!this.selectedFile) {
      console.warn('⚠️ No resume file selected.');
      return;
    }

    const formData = new FormData();
    formData.append('file', this.selectedFile);

    this.http.post('http://localhost:8080/api/resume/upload', formData, {
      responseType: 'text'
    }).subscribe({
      next: (response) => {
        console.log('✅ Resume uploaded:', response);
        this.uploadSuccess = true;
        this.resumeUploaded = true;
        this.checkAndNavigate();
      },
      error: (err) => {
        console.error('❌ Resume upload failed:', err);
      }
    });
  }

  onUploadJD(): void {
    if (!this.jobTitle || !this.jobContent) {
      console.warn('⚠️ JD title or content missing.');
      return;
    }

    const payload = {
      title: this.jobTitle,
      content: this.jobContent
    };

    this.http.post('http://localhost:8080/api/jd/upload', payload, {
      responseType: 'text'
    }).subscribe({
      next: (response) => {
        console.log('✅ JD uploaded:', response);
        this.jdUploadSuccess = true;
        this.jdUploaded = true;
        this.checkAndNavigate();
      },
      error: (err) => {
        console.error('❌ JD upload failed:', err);
      }
    });
  }

  private checkAndNavigate(): void {
    console.log(`🧪 checkAndNavigate - resumeUploaded: ${this.resumeUploaded}, jdUploaded: ${this.jdUploaded}`);
    if (this.resumeUploaded && this.jdUploaded) {
      console.log('✅ Both uploads completed. Navigating to results...');
      this.router.navigate(['/results']).catch(err => {
        console.error('Navigation error:', err);
      });
    }
  }
}
