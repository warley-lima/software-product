import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-toolbar',
  templateUrl: './toolbar.component.html',
  styleUrls: ['./toolbar.component.scss']
})
export class ToolbarComponent implements OnInit {

  @Input() action: string;
  constructor() { }

  ngOnInit() {
  }


/*  onVoted(b: string) {
    console.log('Componente ----> FILHO 1' + b);
  }

  teste2() {
    console.log('Componente ----> FILHO 1 CARAIO');
  } */

}
