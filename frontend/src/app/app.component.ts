import { Component, ViewChild } from '@angular/core';
import { EventBusService } from './servicos/event-bus/event-bus.service';
import { CabecalhoComponent } from './componentes/cabecalho/cabecalho.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  title = 'frontend';

  @ViewChild(CabecalhoComponent)
  cabecalhoComponent!: CabecalhoComponent;

  constructor(private eventBusService: EventBusService) {
    this.eventBusService.eventoDinamico.subscribe(() => {
      this.cabecalhoComponent.botaoLogoff = true;
    });
  }
}
