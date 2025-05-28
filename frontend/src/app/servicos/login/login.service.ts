import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Authentication } from 'src/app/interfaces/authentication';
import { Usuario } from 'src/app/interfaces/usuario';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private loginUrl = `${environment.baseApiUrl}/auth/login`

  constructor(private httpClient: HttpClient) {

  }

  login(usuario: Usuario): Observable<Authentication> {
    return this.httpClient.post<Authentication>(this.loginUrl, usuario)
  }
}
