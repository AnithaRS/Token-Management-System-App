import { Routes } from '@angular/router';

export const routes: Routes = [
  { path: '', loadComponent: () => import('./customer/customer').then(m => m.CustomerComponent) },
  { path: 'display', loadComponent: () => import('./display/display').then(m => m.Display) },
  { path: 'admin', loadComponent: () => import('./admin/admin').then(m => m.Admin) },
]; 