import { Component, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Pessoa } from 'src/app/interfaces/pessoa';
import { CadPessoaService } from 'src/app/servicos/cad-pessoa/cad-pessoa.service';
import { SaveEditComponent } from '../save-edit/save-edit.component';
import { Resposta } from 'src/app/interfaces/resposta';
import { FormControl } from '@angular/forms';
import { MesAnoDatepickerComponent } from 'src/app/componentes/mes-ano-datepicker/mes-ano-datepicker.component';
import moment from 'moment';
import { BuscaPessoaComponent } from 'src/app/componentes/busca-pessoa/busca-pessoa.component';
import { EventBusService } from 'src/app/servicos/event-bus/event-bus.service';
import { AppConstants } from 'src/app/interfaces/constants';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-cad-pessoa',
  templateUrl: './cad-pessoa.component.html',
  styleUrls: ['./cad-pessoa.component.css']
})
export class CadPessoaComponent implements OnInit {

  filtrosHabilitados: boolean = false;
  pessoas: Pessoa[] = [];

  @ViewChild(MesAnoDatepickerComponent)
  mesAnoDatePickerComponent!: MesAnoDatepickerComponent;

  @ViewChild(BuscaPessoaComponent)
  buscaPessoaComponent!: BuscaPessoaComponent;  

  constructor(private cadPessoaService: CadPessoaService, 
              private matDialog: MatDialog,
              private eventBusService: EventBusService,
              private toastrService: ToastrService) {
    
  }

  getLabelSalvar(): string {
    return AppConstants.SALVAR;
  }

  getLabelEditar(): string {
    return AppConstants.EDITAR;
  }

  ngOnInit(): void {
    this.eventBusService.eventoDinamico.emit();
    this.carregaListaUsuarios();
  }

  habilitarDesabilitarCheckbox(): void {
    this.mesAnoDatePickerComponent.habilitarDesabilitarComponente();
    this.buscaPessoaComponent.limparPessoaNome();
    this.carregaListaUsuarios();
  }

  salvarOuEditarPessoa(acao: string, id?: number): void {
    this.matDialog.open(SaveEditComponent, {
      data: {
        id: id, 
        acao: acao
      },
      width: '400px',
      height: '600px'
    }).afterClosed().subscribe(() => {
      this.carregaListaUsuarios()
    })
  }

  excluirPessoa(id: number): void {
    this.cadPessoaService.excluirPessoa(id).subscribe(() => {
      this.carregaListaUsuarios();
      this.toastrService.success(AppConstants.EXCLUSAO_SUCESSO, AppConstants.SUCESSO);
    }, error => {
      this.toastrService.error(AppConstants.EXCLUSAO_ERRO + error.getMessage(), AppConstants.ERRO)
    })
  }

  buscarPessoaNomeReceiver(nome: string): void {
    this.cadPessoaService.buscarPessoaNome(nome).subscribe((resposta) => {
      this.criaListaUsuario(resposta);
    })
  }

  buscarPessoaMesEAnoReceiver(formControl: FormControl): void {
    if(formControl.value) {
      let mesEAno: Date = moment(formControl.value).toDate();
      this.cadPessoaService.buscarPessoaPorMesEAno(mesEAno).subscribe((resposta) => {
        this.criaListaUsuario(resposta)
      });
    }
  }

  private carregaListaUsuarios(): void {
    this.cadPessoaService.listarTodos().subscribe((resposta) => {
      this.criaListaUsuario(resposta);
    })
  }  

  private criaListaUsuario(resposta: Resposta): void {
    const content = resposta.content
    content.map(item => [
      // formatando saída para formato pt-BR apenas com data sem hora
      item.nascimento = new Date(item.nascimento + 'T00:00:00').toLocaleDateString('pt-BR', {year: 'numeric', month: '2-digit', day: '2-digit'})
    ])

    this.pessoas = content.map(item => {
      return {
        id: item.id,
        email: item.email,
        nascimento: item.nascimento,
        nome: item.nome,
        telefone: item.telefone
      }
    })
  }
}