import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Pessoa } from 'src/app/interfaces/pessoa';
import { CadPessoaService } from 'src/app/servicos/cad-pessoa/cad-pessoa.service';
import { ToastrService } from 'ngx-toastr';
import { AppConstants } from 'src/app/interfaces/constants';
import { Erro } from 'src/app/interfaces/erro';

@Component({
  selector: 'app-excluir',
  templateUrl: './excluir.component.html',
  styleUrls: ['./excluir.component.css']
})
export class ExcluirComponent {
  
  pessoa!: Pessoa;

  constructor(@Inject(MAT_DIALOG_DATA) private data: {pessoa: Pessoa},
              private cadPessoaService: CadPessoaService,
              private toastrService: ToastrService,
              private dialogRef: MatDialogRef<ExcluirComponent> /* Usa a própria referência do dialog */) {
    this.pessoa = this.data.pessoa;
  }

  excluir(id: number): void {
    this.cadPessoaService.excluirPessoa(id).subscribe(() => {
      this.toastrService.success(AppConstants.EXCLUSAO_SUCESSO, AppConstants.SUCESSO);
      this.dialogRef.close();
    }, error => {
      const erro: Erro = new Erro(error);
      this.toastrService.error(AppConstants.EXCLUSAO_ERRO + erro.getMessage(), AppConstants.ERRO)
    })
  }
}