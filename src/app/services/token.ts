import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

export interface ApiResponse<T> {
  success: boolean;
  message: string;
  data: T;
}

export interface TokenQueueResponse {
  id: number;
  tokenNumber: number;
  status: string;
  counterCode: string | null;
  displayName: string | null;
}

export interface DisplayTokenResponse {
  tokenNumber: number;
  counterCode: string;
  counterName: string | null;
  displayName: string;
  status: string;
}

@Injectable({
  providedIn: 'root'
})
export class TokenService {
  private baseUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) {}

  createToken(): Observable<ApiResponse<TokenQueueResponse>> {
    return this.http.post<ApiResponse<TokenQueueResponse>>(`${this.baseUrl}/tokens`, {});
  }

  getWaitingTokens(): Observable<ApiResponse<TokenQueueResponse[]>> {
    return this.http.get<ApiResponse<TokenQueueResponse[]>>(`${this.baseUrl}/tokens/waiting`);
  }

  getCurrentlyCalledTokens(): Observable<ApiResponse<DisplayTokenResponse[]>> {
    return this.http.get<ApiResponse<DisplayTokenResponse[]>>(`${this.baseUrl}/display/current`);
  }

  callNextToken(counterCode: string): Observable<ApiResponse<DisplayTokenResponse>> {
    return this.http.post<ApiResponse<DisplayTokenResponse>>(`${this.baseUrl}/display/call-next`, { counterCode });
  }

  completeCurrentToken(counterCode: string): Observable<ApiResponse<any>> {
    return this.http.post<ApiResponse<any>>(`${this.baseUrl}/display/complete`, { counterCode });
  }
}