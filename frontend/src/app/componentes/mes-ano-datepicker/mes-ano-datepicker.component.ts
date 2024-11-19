import {AfterViewInit, ChangeDetectionStrategy, Component, EventEmitter, Input, OnInit, Output, ViewEncapsulation} from '@angular/core';
import {FormControl, FormsModule, ReactiveFormsModule} from '@angular/forms';
import {provideMomentDateAdapter} from '@angular/material-moment-adapter';
import {MatDatepicker, MatDatepickerModule} from '@angular/material/datepicker';
import * as _moment from 'moment';
import {default as _rollupMoment, Moment} from 'moment';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';

const moment = _rollupMoment || _moment;

export const MY_FORMATS = {
  parse: {
    dateInput: 'MM/YYYY',
  },
  display: {
    dateInput: 'MM/YYYY',
    monthYearLabel: 'MMM YYYY',
    dateA11yLabel: 'LL',
    monthYearA11yLabel: 'MMMM YYYY',
  },
};

@Component({
  selector: 'app-mes-ano-datepicker',
  templateUrl: './mes-ano-datepicker.component.html',
  styleUrls: ['./mes-ano-datepicker.component.css'],
  providers: [
    provideMomentDateAdapter(MY_FORMATS),
  ],
  encapsulation: ViewEncapsulation.None,
  standalone: true,
  imports: [
    MatFormFieldModule,
    MatInputModule,
    MatDatepickerModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  changeDetection: ChangeDetectionStrategy.OnPush,  
})
export class MesAnoDatepickerComponent implements AfterViewInit {

  readonly date = new FormControl(moment());

  @Input()
  desabilitarInput!: boolean;
  
  @Output()
  buscarPessoaMesEAnoEmitter: EventEmitter<FormControl> = new EventEmitter();

  ngAfterViewInit(): void {
    this.date.disable();
    this.date.setValue(null);
    this.date.valueChanges.subscribe(() => {
      this.buscarPessoaMesEAnoEmitter.emit(this.date);
    })
  }

  habilitarDesabilitarComponente(): void {
    if(this.desabilitarInput) {
      this.date.enable();
    } else {
      this.date.disable();
    }
  }

  setMonthAndYear(normalizedMonthAndYear: Moment, datepicker: MatDatepicker<Moment>) {
    const ctrlValue = this.date.value ?? moment();
    ctrlValue.month(normalizedMonthAndYear.month());
    ctrlValue.year(normalizedMonthAndYear.year());
    this.date.setValue(ctrlValue);
    datepicker.close();
  }
}