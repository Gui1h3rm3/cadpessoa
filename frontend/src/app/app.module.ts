import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CabecalhoComponent } from './componentes/cabecalho/cabecalho.component';
import { LoginComponent } from './paginas/login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';
import { CadPessoaComponent } from './paginas/cad-pessoa/cad-pessoa.component';
import { SaveEditComponent } from './paginas/save-edit/save-edit.component';
import { PessoaFormComponent } from './componentes/pessoa-form/pessoa-form.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatDialogModule } from '@angular/material/dialog';
import { BuscaPessoaComponent } from './componentes/busca-pessoa/busca-pessoa.component';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatNativeDateModule, MAT_DATE_FORMATS } from '@angular/material/core';
import { MesAnoDatepickerComponent } from './componentes/mes-ano-datepicker/mes-ano-datepicker.component';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MensagensComponent } from './componentes/mensagens/mensagens.component';
import { ToastrModule } from 'ngx-toastr';
import { ExcluirComponent } from './paginas/excluir/excluir.component';

@NgModule({ declarations: [
        AppComponent,
        CabecalhoComponent,
        LoginComponent,
        CadPessoaComponent,
        SaveEditComponent,
        PessoaFormComponent,
        BuscaPessoaComponent,
        MensagensComponent,
        ExcluirComponent
    ],
    bootstrap: [AppComponent], imports: [BrowserModule,
        AppRoutingModule,
        FormsModule,
        ReactiveFormsModule,
        BrowserAnimationsModule,
        MatDialogModule,
        MatDatepickerModule,
        MatFormFieldModule,
        MatInputModule,
        MatNativeDateModule,
        MesAnoDatepickerComponent,
        MatCheckboxModule,
        BrowserAnimationsModule,
        ToastrModule.forRoot({
            timeOut: 5000,
            positionClass: 'toast-bottom-right',
            preventDuplicates: true,
            progressBar: true,
            closeButton: true
        })], providers: [provideHttpClient(withInterceptorsFromDi())] })
export class AppModule { }
