import { Component } from '@angular/core';
import { MensagensService } from 'src/app/servicos/mensagens/mensagens.service';

@Component({
  selector: 'app-mensagens',
  templateUrl: './mensagens.component.html',
  styleUrls: ['./mensagens.component.css']
})
export class MensagensComponent {
  constructor(public mensagensService: MensagensService) {
    
  }
}
