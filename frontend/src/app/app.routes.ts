import { Routes } from '@angular/router';
import { WelcomeComponent } from './pages/welcome/welcome';
import { Upload } from './pages/upload/upload';
import { MatchResults } from './pages/match-results/match-results';
import { Login } from './pages/login/login';
import { RegisterComponent } from './pages/register/register';
import { HomeComponent } from './pages/home/home';
import { DashboardComponent } from './pages/dashboard/dashboard';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: Login },
  { path: 'welcome', component: WelcomeComponent },
  { path: 'upload', component: Upload },
  { path: 'results', component: MatchResults },
  { path: 'register', component: RegisterComponent },
  { path: 'home', component: HomeComponent},
  { path: 'dashboard', component: DashboardComponent},
];
