import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-base-alert-modal',
  template: `
  <div class="modal fade" id="{{idModal}}" style="z-index: 1072; overflow-y: auto; overflow-x: hidden;"
  tabindex="-1" role="dialog" aria-labelledby="myModalTit" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title font-weight-bold" id="myModalTit">{{ title }}</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          {{  body  }}
        </div>
        <ng-content></ng-content>
      </div>
  </div>
</div>
  `,
  styles: [
    `
    h5, .h5 {
      font-size: 1.25rem;
      font-weight: 500;
  }
    `
  ]
})
export class BaseAlertModalComponent implements OnInit {
  @Input() title: string;
  @Input() body: string;
  @Input() idModal: string;
  constructor() { }

  ngOnInit() {
  }

}
