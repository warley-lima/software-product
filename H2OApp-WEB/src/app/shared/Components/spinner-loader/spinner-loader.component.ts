import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { Subscription } from 'rxjs';
import { SpinnerLoadService } from '../../services/spinner-load.service';
import { LoaderState } from '../../Utils/LoaderState';
declare let $: any;
@Component({
  selector: 'app-spinner-loader',
  template: `
  <div [class.hidden]="!show">
		<div class="loader-overlay ">
			<div *ngIf="show" class="loader mx-auto"></div>
		</div>
	</div>
  `,
  styles: [
    `.hidden {
        visibility: hidden;
      }
     .loader-overlay {
        width: 100%;
        height: 100%;
        z-index: 500000;
        top: 50;
        position: relative;
        overflow: hidden;
      }
      .loader {
        border: 16px solid #f3f3f3;
        border-radius: 50%;
        border-top: 1rem solid blue;
        border-bottom: 1rem solid blue;
        width: 7.5rem;
        height: 7.5rem;
        -webkit-animation: spin 2s linear infinite;
        animation: spin 2s linear infinite;
      }
      @-webkit-keyframes spin {
        0% { -webkit-transform: rotate(0deg); }
        100% { -webkit-transform: rotate(360deg); }
      }
      @keyframes spin {
        0% { transform: rotate(0deg); }
        100% { transform: rotate(360deg); }
      }
      `
  ]
})
export class SpinnerLoaderComponent implements OnInit, OnDestroy {
  private inscSpinnerLoad: Subscription;
  show = true;
  @Input() isShow: string;
  constructor(private shwHideService: SpinnerLoadService) { }

  ngOnInit() {
         if ('n' === this.isShow) {
          this.show = false;
         } else {
          this.show = true;
         }
         this.inscSpinnerLoad = this.shwHideService.loaderState
         .subscribe((state: LoaderState) => {
           this.show = state.show;
         });
  }

  ngOnDestroy(): void {
   this.inscSpinnerLoad.unsubscribe();
   this.show = false;
  }
}
