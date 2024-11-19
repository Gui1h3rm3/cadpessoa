import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MesAnoDatepickerComponent } from './mes-ano-datepicker.component';

describe('MesAnoDatepickerComponent', () => {
  let component: MesAnoDatepickerComponent;
  let fixture: ComponentFixture<MesAnoDatepickerComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MesAnoDatepickerComponent]
    });
    fixture = TestBed.createComponent(MesAnoDatepickerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
