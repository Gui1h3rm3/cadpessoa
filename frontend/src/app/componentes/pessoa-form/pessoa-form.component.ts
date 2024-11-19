import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Pessoa } from 'src/app/interfaces/pessoa';
import { CadPessoaService } from 'src/app/servicos/cad-pessoa/cad-pessoa.service';

@Component({
  selector: 'app-pessoa-form',
  templateUrl: './pessoa-form.component.html',
  styleUrls: ['./pessoa-form.component.css']
})
export class PessoaFormComponent implements OnInit {
  
  pessoa?: Pessoa
  pessoaForm!: FormGroup

  @Input()
  id?: number

  @Output()
  salvarEmitter: EventEmitter<Pessoa> = new EventEmitter()

  @Output()
  cancelarEmitter: EventEmitter<any> = new EventEmitter()

  constructor(private cadPessoaService: CadPessoaService) {

  }

  ngOnInit(): void {
    this.pessoaForm = new FormGroup({
      id: new FormControl(''),
      nome: new FormControl('', [Validators.required]),
      email: new FormControl(''),
      nascimento: new FormControl('', [Validators.required]),
      telefone: new FormControl('')
    })

    if(this.id != undefined) {
      this.cadPessoaService.buscaPessoaPorId(this.id).subscribe(p => {
        this.pessoaForm.patchValue({
          id: p.id,
          nome: p.nome,
          email: p.email,
          nascimento: p.nascimento,
          telefone: p.telefone
        });
      });
    }    
  }

  salvar(): void {

    if(this.pessoaForm.invalid) {
      return
    }

    this.salvarEmitter.emit(this.pessoaForm.value)
  }

  cancelar(): void {
    this.cancelarEmitter.emit()
  }

  get nome() {
    return this.pessoaForm.get('nome')
  }

  get email() {
    return this.pessoaForm.get('email')
  }

  get nascimento() {
    return this.pessoaForm.get('nascimento')
  }

  get telefone() {
    return this.pessoaForm.get('telefone')
  }
}