import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CadPessoaComponent } from './cad-pessoa.component';

describe('CadPessoaComponent', () => {
  let component: CadPessoaComponent;
  let fixture: ComponentFixture<CadPessoaComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CadPessoaComponent]
    });
    fixture = TestBed.createComponent(CadPessoaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
