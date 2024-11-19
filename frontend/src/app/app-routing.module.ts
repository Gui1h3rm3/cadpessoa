import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './paginas/login/login.component';
import { CadPessoaComponent } from './paginas/cad-pessoa/cad-pessoa.component';
import { SaveEditComponent } from './paginas/save-edit/save-edit.component';

const routes: Routes = [
  {path: '', component: LoginComponent},
  {path: 'cad-pessoa', component: CadPessoaComponent},
  {path: 'save-edit', component: SaveEditComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
