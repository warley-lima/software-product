import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-base-modal',
  template: `
  <div class="modal fade" id="{{idModal}}" style="z-index: 1072; overflow-y: auto; overflow-x: hidden;"
  tabindex="-1" role="dialog" aria-labelledby="myModalTit" aria-hidden="true">
  <div class="modal-dialog {{classTamanho}} modal-dialog-centered" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title font-weight-bold" id="myModalTit">{{ title }}</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
            <span aria-hidden="true">&times;</span>
          </button>
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
  /*.modal-open {
    overflow: scroll;
  }*/
    `
  ]
})
export class BaseModalComponent implements OnInit {
  @Input() idModal: string;
  @Input() title: string;
  @Input() classTamanho: string;
  constructor() { }

  ngOnInit() {
  }

}
