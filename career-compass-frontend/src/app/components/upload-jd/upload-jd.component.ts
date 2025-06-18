// File: frontend/src/app/components/upload-jd/upload-jd.component.ts
import { Component } from '@angular/core';
import { ResumeService } from '../../services/resume.service';
import { FormsModule } from '@angular/forms'; // ✅ Required for ngModel

@Component({
  selector: 'app-upload-jd',
  standalone: true, // ✅ Required for standalone components
  imports: [FormsModule], // ✅ Allow ngModel to work
  templateUrl: './upload-jd.component.html',
  styleUrls: ['./upload-jd.component.css']
})
export class UploadJDComponent {
  jdTitle: string = '';
  jdContent: string = '';
  uploadMessage: string = '';
  isUploading = false;

  constructor(private jdService: ResumeService) {}

  onUpload() {
    if (this.jdTitle && this.jdContent) {
      this.isUploading = true;
      this.jdService.uploadJD({ title: this.jdTitle, content: this.jdContent }).subscribe({
        next: (res) => {
          this.uploadMessage = 'Job Description uploaded successfully!';
          this.isUploading = false;
        },
        error: (err) => {
          this.uploadMessage = 'Failed to upload Job Description.';
          console.error(err);
          this.isUploading = false;
        }
      });
    } else {
      this.uploadMessage = 'Please provide both title and content.';
    }
  }
}
