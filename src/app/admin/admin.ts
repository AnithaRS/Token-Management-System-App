import { Component, OnInit, OnDestroy, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { TokenService, TokenQueueResponse, DisplayTokenResponse } from '../services/token';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-admin',
  standalone: true,
  imports: [CommonModule, FormsModule,RouterModule],
  templateUrl: './admin.html',
  styleUrl: './admin.css'
})
export class Admin implements OnInit, OnDestroy {
  waitingTokens: TokenQueueResponse[] = [];
  calledTokens: DisplayTokenResponse[] = [];
  selectedCounter: string = 'C1';
  counters: string[] = ['C1', 'C2', 'C3'];

  message = '';
  isError = false;

  private refreshInterval: any;

  constructor(
    private tokenService: TokenService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.refreshData();
    this.refreshInterval = setInterval(() => {
      this.refreshData();
    }, 3000);
  }

  ngOnDestroy(): void {
    if (this.refreshInterval) {
      clearInterval(this.refreshInterval);
    }
  }

  refreshData(): void {
    this.tokenService.getWaitingTokens().subscribe({
      next: (response: any) => {
        this.waitingTokens = response.data || [];
        this.cdr.detectChanges();
      },
      error: (err: any) => console.error('Error loading waiting tokens:', err)
    });

    this.tokenService.getCurrentlyCalledTokens().subscribe({
      next: (response: any) => {
        this.calledTokens = response.data || [];
        this.cdr.detectChanges();
      },
      error: (err: any) => console.error('Error loading called tokens:', err)
    });
  }

  createToken(): void {
    this.tokenService.createToken().subscribe({
      next: (response: any) => {
        this.showMessage(`Token ${response.data.tokenNumber} created successfully`, false);
        this.refreshData();
      },
      error: (err: any) => {
        this.showMessage('Failed to create token. Please try again.', true);
      }
    });
  }

  callNext(): void {
    this.tokenService.callNextToken(this.selectedCounter).subscribe({
      next: (response: any) => {
        this.showMessage(`Token ${response.data.tokenNumber} is now called at ${this.selectedCounter}`, false);
        this.refreshData();
      },
      error: (err: any) => {
        const errMsg = err?.error?.message || `Counter ${this.selectedCounter} is already serving a token. Please complete the current token before calling the next one.`;
        this.showMessage(errMsg, true);
      }
    });
  }

  completeCurrent(): void {
    this.tokenService.completeCurrentToken(this.selectedCounter).subscribe({
      next: (response: any) => {
        this.showMessage(`Token at ${this.selectedCounter} marked as completed`, false);
        this.refreshData();
      },
      error: (err: any) => {
        const errMsg = err?.error?.message || `No active token to complete at ${this.selectedCounter}.`;
        this.showMessage(errMsg, true);
      }
    });
  }

  showMessage(msg: string, isError: boolean): void {
    this.message = msg;
    this.isError = isError;
    this.cdr.detectChanges();
  }
}