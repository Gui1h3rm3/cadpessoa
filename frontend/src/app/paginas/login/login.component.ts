import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Authentication } from 'src/app/interfaces/authentication';
import { Usuario } from 'src/app/interfaces/usuario';
import { LoginService } from 'src/app/servicos/login/login.service';
import { TokenService } from 'src/app/servicos/token/token.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  authentication = {} as Authentication
  
  loginForm!: FormGroup

  ngOnInit(): void {
    this.loginForm = new FormGroup({
      username: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required])
    })
  }

  constructor(private loginService: LoginService, 
              private tokenService: TokenService, 
              private router: Router) {

  }

  submit() {

    if(this.loginForm.invalid) {
      return
    }

    this.loginService.login(this.loginForm.value).subscribe((auth) => {
      this.authentication = auth
      let token: string = <string>this.authentication.token
      this.tokenService.setToken(token)
      this.router.navigate(['/cad-pessoa'])
    }, (error) => {
      console.error("Erro:" + error)
    })
  }

  get username() {
    return this.loginForm.get('username')
  }

  get password(){
    return this.loginForm.get('password')
  }
}
