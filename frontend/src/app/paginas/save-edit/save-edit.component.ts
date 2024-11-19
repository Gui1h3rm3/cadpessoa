import { Component, EventEmitter, Inject, OnInit, Output } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Pessoa } from 'src/app/interfaces/pessoa';
import { CadPessoaService } from 'src/app/servicos/cad-pessoa/cad-pessoa.service';

@Component({
  selector: 'app-save-edit',
  templateUrl: './save-edit.component.html',
  styleUrls: ['./save-edit.component.css']
})
export class SaveEditComponent {

  acao: string
  id?: number

  constructor(private router: Router,
              private cadPessoaService: CadPessoaService, 
              private matDialog: MatDialogRef<SaveEditComponent>, 
              @Inject(MAT_DIALOG_DATA) 
              private data: {acao: string, id?: number}) {
    this.acao = this.data.acao
    this.id = this.data.id
  }

  salvarPessoaReceiver(pessoa: Pessoa): void {
    this.cadPessoaService.salvarPessoa(pessoa).subscribe(response => {
      this.closeDialogReceiver()
    }, error => {
      console.log('Erro:', error)
    })
  }

  closeDialogReceiver(): void {
    this.matDialog.close();
  }
}