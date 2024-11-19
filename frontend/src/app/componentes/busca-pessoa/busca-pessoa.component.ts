import { Component, EventEmitter, OnInit, Input, Output, SimpleChanges } from '@angular/core';

@Component({
  selector: 'app-busca-pessoa',
  templateUrl: './busca-pessoa.component.html',
  styleUrls: ['./busca-pessoa.component.css']
})
export class BuscaPessoaComponent implements OnInit {

  nome: string | undefined;

  @Input()
  desabilitarInput!: boolean;

  @Output()
  buscarPessoaNomeEmitter: EventEmitter<string> = new EventEmitter();

  ngOnInit(): void {
    this.nome = '';
  }

  buscarPessoaNome(nome: string): void {
    this.buscarPessoaNomeEmitter.emit(nome);
  }
}
