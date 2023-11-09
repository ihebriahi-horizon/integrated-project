import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-top',
  templateUrl: './top.component.html',
  styleUrls: ['./top.component.scss']
})
export class TopComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  get currentUrl() {
    let url = this.router.url;
    if (url.endsWith('#top')) {
      url = url.slice(0, -4);
    }
    return url;
  }
}
