import { Component, OnInit, Input } from '@angular/core';

import { ToolbarUtils } from './../../Utils/toolbar-utils';
@Component({
  selector: 'app-item-w-icon',
  template: `
    <div class="icon-toolbar"  (click)="teste3(action)">
      <span [class]="srcImg"></span>
      <div class="nameOption">{{ label }}</div>
    </div>
  `,
  styles: [
    `
      .icon-toolbar {
        padding: 1px 1px 3px 4px;
        text-align: center;
        color: #666;
        height: 3rem;
        float: left;
      }
      .icon-img{
        float: none;
        width: 2rem;
        height: 2rem;
        margin: 0 auto;
        display: block;
      }
      .icon-32-new {
        background-image: url(assets/img/toolbar/icon-32-new.png);
      }
      .icon-32-search {
        background-image: url(assets/img/toolbar/icon-32-search.png);
      }
      .nameOption {
        color: #025A8D;
        font-weight: bold;
        font-size: 0.75rem;
        padding: 0;
        margin: 0;
    }
    `
  ]
})
export class ItemWIconComponent implements OnInit {
  @Input() srcImg: string;
  @Input() label: string;
  @Input() action: string;
  constructor( private toolBarEvEmitter: ToolbarUtils) {}
  ngOnInit() {}

  teste3(a: string) {
    this.toolBarEvEmitter.clickItemToolbarEmitter(a);
  }

  teste2() { }
}
