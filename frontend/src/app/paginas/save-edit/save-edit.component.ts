import { Component, EventEmitter, Inject, OnInit, Output } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { AppConstants } from 'src/app/interfaces/constants';
import { Erro } from 'src/app/interfaces/erro';
import { Pessoa } from 'src/app/interfaces/pessoa';
import { CadPessoaService } from 'src/app/servicos/cad-pessoa/cad-pessoa.service';
import { MensagensService } from 'src/app/servicos/mensagens/mensagens.service';

@Component({
  selector: 'app-save-edit',
  templateUrl: './save-edit.component.html',
  styleUrls: ['./save-edit.component.css']
})
export class SaveEditComponent {

  acao: string
  id?: number

  constructor(private mensagensService: MensagensService,
              private cadPessoaService: CadPessoaService, 
              private matDialog: MatDialogRef<SaveEditComponent>, 
              @Inject(MAT_DIALOG_DATA) 
              private data: {acao: string, id?: number},
              private toastrService: ToastrService) {
    this.acao = this.data.acao
    this.id = this.data.id
  }

  salvarPessoaReceiver(pessoa: Pessoa): void {
    this.cadPessoaService.salvarPessoa(pessoa).subscribe(() => {      
      const mensagem: string = this.acao === AppConstants.SALVAR ? AppConstants.INCLUSAO_SUCESSO : AppConstants.ALTERACAO_SUCESSO;
      this.toastrService.success(mensagem, AppConstants.SUCESSO);
      this.closeDialogReceiver();
    }, error => {
      const erro: Erro = new Erro(error);
      const mensagem: string = this.acao === AppConstants.SALVAR
      ? `Ocorreu um erro ao cadastrar a pessoa: ${erro.getMessage()} - caminho: ${erro.getPath()} - código: ${erro.getStatusNumber()} - tipo: ${erro.getStatus()}`
      : `Ocorreu um erro ao alterar a pessoa: ${erro.getMessage()} - caminho: ${erro.getPath()} - código: ${erro.getStatusNumber()} - tipo: ${erro.getStatus()}`;
      this.toastrService.error(mensagem, AppConstants.ERRO);
    })
  }

  closeDialogReceiver(): void {
    this.matDialog.close();
  }
}