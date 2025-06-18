import { Component } from '@angular/core';
import { RouterLink } from '@angular/router'; // ✅ Import RouterLink
import { CommonModule } from '@angular/common'; // Optional but often useful

@Component({
  selector: 'app-welcome',
  standalone: true,
  imports: [CommonModule, RouterLink], // ✅ Use RouterLink here
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent {}
