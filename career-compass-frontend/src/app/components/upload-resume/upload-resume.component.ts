// File: frontend/src/app/components/upload-resume/upload-resume.component.ts
import { Component } from '@angular/core';
import { ResumeService } from '../../services/resume.service';

@Component({
  selector: 'app-upload-resume',
  templateUrl: './upload-resume.component.html',
  styleUrls: ['./upload-resume.component.css']
})
export class UploadResumeComponent {
  selectedFile!: File;
  isUploading = false;
  uploadMessage = '';

  constructor(private resumeService: ResumeService) {}

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
  }

  onUpload() {
    if (this.selectedFile) {
      this.isUploading = true;
      this.resumeService.uploadResume(this.selectedFile).subscribe({
        next: (response) => {
          this.uploadMessage = 'Resume uploaded successfully!';
          this.isUploading = false;
        },
        error: (err) => {
          this.uploadMessage = 'Failed to upload resume.';
          console.error(err);
          this.isUploading = false;
        }
      });
    }
  }
}
