import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-base-list-group',
  template: `
  <div (click)="changeClass()" id="{{idItem}}"
  class="list-group-item list-group-item-action flex-column align-items-start mb-3 div-lst {{classActive}}">
    <div class="d-flex w-100 ">
        <img src="{{srcImg}}" class="img-thumbnail img-lst responsive-img" alt="Logo" *ngIf="showLogo">
        <h5 class="space-brands text-center">{{ name }}</h5>
    </div>
  </div>
  `,
  styles: [
    `
      .div-lst{
        height: 5.625rem;
        cursor:pointer;
      }
      .space-brands{
        margin-top: -0.15rem;
      }
      .img-lst{
        height: 4rem;
        min-width:  6rem;
        max-width:  6rem;
      }
      @media (min-width: 39.93em) {
        .space-brands{
          margin-top: 0.75rem;
          margin-left: 0.25rem;
        }
      }
    `
  ]
})
export class BaseListGroupComponent implements OnInit {
  @Input() idItem: string;
  @Input() name: string;
  classActive: string;
  @Input() srcImg: string;
  @Input() showLogo: boolean;
  clicked = false;
  constructor() { }

  ngOnInit() {
  }

  changeClass() {
    if ( false === this.clicked ) {
      this.classActive = 'active';
      this.clicked = true;
    } else {
      this.classActive = '';
      this.clicked = false;
    }

  }

}
