import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BuscaPessoaComponent } from './busca-pessoa.component';

describe('BuscaPessoaComponent', () => {
  let component: BuscaPessoaComponent;
  let fixture: ComponentFixture<BuscaPessoaComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BuscaPessoaComponent]
    });
    fixture = TestBed.createComponent(BuscaPessoaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
