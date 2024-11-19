import { TestBed } from '@angular/core/testing';

import { CadPessoaService } from './cad-pessoa.service';

describe('CadPessoaService', () => {
  let service: CadPessoaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CadPessoaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
