import { ItemWIconComponent } from './../item-w-icon/item-w-icon.component';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-sidebar-menu',
  templateUrl: './sidebar-menu.component.html',
  styleUrls: ['./sidebar-menu.component.scss']
})
export class SidebarMenuComponent implements OnInit {

  constructor() {
   // super();
   }

  ngOnInit() {
    this.teste();
  }

 teste() {
    console.log('Componente FILHO');
  }


 teste2() {
  console.log('Componente FILHO CARAIO');
}


}
