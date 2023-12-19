import { Component } from '@angular/core';

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.scss'],
})
export class AboutComponent {
  constructor() { }
  options: any;

  overlays!: any[];

  setMap(event) {
  }

  ngOnInit(): void {

  }
}
