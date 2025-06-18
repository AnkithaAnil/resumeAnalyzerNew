import { Routes } from '@angular/router';
import { WelcomeComponent } from './pages/welcome/welcome';
import { Upload } from './pages/upload/upload';
import { MatchResults } from './pages/match-results/match-results';


export const routes: Routes = [
  { path: '', component: WelcomeComponent },
  { path: 'upload', component: Upload },
  { path: 'results', component: MatchResults },
];

