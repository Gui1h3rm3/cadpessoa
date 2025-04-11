import { ChangeDetectorRef, Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { TokenService } from 'src/app/servicos/token/token.service';

@Component({
  selector: 'app-cabecalho',
  templateUrl: './cabecalho.component.html',
  styleUrls: ['./cabecalho.component.css']
})
export class CabecalhoComponent {

  botaoLogoff: boolean;

  constructor(private tokenService: TokenService, 
              private router: Router, 
              private cdr: ChangeDetectorRef) {
    this.botaoLogoff = false;
  }

  logoff(): void {
    this.tokenService.removeToken();
    this.botaoLogoff = false;
    this.router.navigate([""]);
  }
}