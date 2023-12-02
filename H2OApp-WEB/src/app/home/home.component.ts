import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { ToolbarUtils } from '../shared/Utils/toolbar-utils';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit, OnDestroy {
  private inscToolBarEmiter: Subscription;

  constructor(
    private toolBarEvEmitter: ToolbarUtils
    ) { }

  ngOnInit() {
    this.inscToolBarEmiter = this.toolBarEvEmitter.clicked.subscribe(isOpen => {
     this.onVoted2(isOpen);
    });
  }
  ngOnDestroy() {
    this.inscToolBarEmiter.unsubscribe();
  }

  onVoted2(b: string) {
    console.log('Componente FILHO HOME-->' + b);
  }

}
