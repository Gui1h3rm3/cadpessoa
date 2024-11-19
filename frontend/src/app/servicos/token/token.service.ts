import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TokenService {
  private token = "bearer_token"

  constructor() { }

  setToken(token: string): void {
    localStorage.setItem(this.token, token)
  }

  getToken(): string | null {
    return localStorage.getItem(this.token)
  }

  removeToken(): void {
    localStorage.removeItem(this.token)
  }
}
