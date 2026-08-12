import { Component, OnInit, OnDestroy, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TokenService, DisplayTokenResponse } from '../services/token';

@Component({
  selector: 'app-display',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './display.html',
  styleUrl: './display.css'
})
export class Display implements OnInit, OnDestroy {
  calledTokens: DisplayTokenResponse[] = [];
  private refreshInterval: any;

  constructor(
    private tokenService: TokenService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.loadCurrentTokens();
    this.refreshInterval = setInterval(() => {
      this.loadCurrentTokens();
    }, 3000);
  }

  ngOnDestroy(): void {
    if (this.refreshInterval) {
      clearInterval(this.refreshInterval);
    }
  }

  loadCurrentTokens(): void {
    this.tokenService.getCurrentlyCalledTokens().subscribe({
      next: (response: any) => {
        this.calledTokens = response.data || [];
        this.cdr.detectChanges();
      },
      error: (err: any) => {
        console.error('Error loading current tokens:', err);
        this.cdr.detectChanges();
      }
    });
  }
}