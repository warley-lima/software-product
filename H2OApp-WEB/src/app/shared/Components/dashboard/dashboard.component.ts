import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-item-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  @Input() linkFor: string;
  @Input() srcImg: string;
  @Input() label: string;

  constructor() { }

  ngOnInit() {
  }

}
