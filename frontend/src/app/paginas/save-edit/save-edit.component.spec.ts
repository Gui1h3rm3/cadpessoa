import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SaveEditComponent } from './save-edit.component';

describe('SaveEditComponent', () => {
  let component: SaveEditComponent;
  let fixture: ComponentFixture<SaveEditComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SaveEditComponent]
    });
    fixture = TestBed.createComponent(SaveEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
