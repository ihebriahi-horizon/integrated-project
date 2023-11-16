import { Component } from '@angular/core';

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.scss']
})
export class AboutComponent {

  constructor() { }
  options: any;
  map!: google.maps.Map;
  overlays!: any[];



  setMap(event) {
    this.map = event.map;
  }

  ngOnInit(): void {
    let bounds = new google.maps.LatLngBounds();

    this.options = {
      center: { lat: 35.82262115818213, lng: 10.630281722277608 },
      zoom: 17
    }
    this.overlays = [
      new google.maps.Marker({ position: { lat: 35.82262115818213, lng: 10.630281722277608 }, title: "Horizon School" }),
      new google.maps.Polygon({
        paths: [
        ], strokeOpacity: 0.5, strokeWeight: 1, fillColor: '#1976D2', fillOpacity: 0.35
      }),
    ];
    this.overlays.forEach(marker => {
      bounds.extend(marker.getPosition());
    });
    setTimeout(() => { // map will need some time to load
      this.map.fitBounds(bounds); // Map object used directly
    }, 1000);
  }


}
