import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MensagensService {
  private mensagens: Array<string> = [];

  add(msg: string): void {
    this.mensagens.push(msg);
  }

  getMensagens(): Array<string> {
    return this.mensagens;
  }
}
