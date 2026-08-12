import { Component, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TokenService, TokenQueueResponse } from '../services/token';

@Component({
  selector: 'app-customer',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './customer.html',
  styleUrl: './customer.css'
})
export class CustomerComponent {
  loading = false;
  errorMessage = '';
  generatedToken: TokenQueueResponse | null = null;

  constructor(
    private tokenService: TokenService,
    private cdr: ChangeDetectorRef
  ) {}

  getToken(): void {
    this.loading = true;
    this.errorMessage = '';
    this.generatedToken = null;

    this.tokenService.createToken().subscribe({
      next: (response: any) => {
        this.generatedToken = response.data;
        this.loading = false;
        this.cdr.detectChanges();
      },
      error: (err: any) => {
        console.error('Error creating token:', err);
        this.errorMessage = 'Unable to generate token. Please try again or contact staff.';
        this.loading = false;
        this.cdr.detectChanges();
      }
    });
  }

  requestAnother(): void {
    this.generatedToken = null;
    this.errorMessage = '';
    this.cdr.detectChanges();
  }
}