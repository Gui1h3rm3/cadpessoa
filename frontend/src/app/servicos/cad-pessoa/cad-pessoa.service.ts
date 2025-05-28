import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { TokenService } from '../token/token.service';
import { Resposta } from 'src/app/interfaces/resposta';
import { Pessoa } from 'src/app/interfaces/pessoa';

@Injectable({
  providedIn: 'root'
})
export class CadPessoaService {

  private cadPessoaUrl = `${environment.baseApiUrl}/pessoa`

  constructor(private httpClient: HttpClient, 
              private tokenService: TokenService) {

  }
  
  private getHeaders(): HttpHeaders {
    const token = this.tokenService.getToken();
    return new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `${token}`
    })
  }

  listarTodos(): Observable<Resposta> {
    const headers: HttpHeaders = this.getHeaders();
    const url = `${this.cadPessoaUrl}/listAll`
    return this.httpClient.get<Resposta>(url, {headers})
  }
  
  buscaPessoaPorId(id: number): Observable<Pessoa> {
    const headers: HttpHeaders = this.getHeaders();
    const url = `${this.cadPessoaUrl}/getById/${id}`;
    return this.httpClient.get<Pessoa>(url, {headers});
  }

  buscarPessoaNome(nome: string): Observable<Resposta> {
    const headers: HttpHeaders = this.getHeaders();
    const url = `${this.cadPessoaUrl}/filterByName?nome=${nome}`;
    return this.httpClient.get<Resposta>(url, {headers});
  }

  buscarPessoaPorMesEAno(mesEAno: Date): Observable<Resposta> {
    const headers: HttpHeaders = this.getHeaders();
    const mes = mesEAno.getMonth() + 1; 
    const ano = mesEAno.getFullYear();
    const url = `${this.cadPessoaUrl}/filterByMonthAndYear?mes=${mes}&ano=${ano}`;
    return this.httpClient.get<Resposta>(url, {headers});
  }
  
  salvarPessoa(pessoa: Pessoa): Observable<Pessoa> {
    const headers: HttpHeaders = this.getHeaders();
    const url = `${this.cadPessoaUrl}/save`
    return this.httpClient.post<Pessoa>(url, pessoa, {headers})
  }

  excluirPessoa(id: number): Observable<any> {
    const headers: HttpHeaders = this.getHeaders();
    const url = `${this.cadPessoaUrl}/remove`
    return this.httpClient.post(url, {id}, {headers})
  }
}