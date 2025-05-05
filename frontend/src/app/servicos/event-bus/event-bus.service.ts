import { EventEmitter, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EventBusService {
  public eventoDinamico = new EventEmitter<any>();
}
