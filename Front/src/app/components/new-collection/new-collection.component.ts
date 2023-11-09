import { Cart } from './../../models/cart';
import { Observable } from 'rxjs';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { StoreServicesService } from 'src/app/services/store-services.service';
import { NgForm } from '@angular/forms';
import { CartServicesService } from 'src/app/services/cart-services.service';

@Component({
  selector: 'app-new-collection',
  templateUrl: './new-collection.component.html',
  styleUrls: ['./new-collection.component.scss']
})
export class NewCollectionComponent implements OnInit {

  @ViewChild('container', { static: false })
  container!: ElementRef;

  @ViewChild('create') create!: NgForm;
  store$!: Observable<any>;
  cart!: Cart;
  vetor: any;
  displayCount = 6;
  showAll = false;

  constructor(private storeService: StoreServicesService,
    private cartService: CartServicesService) { }

  ngOnInit(): void {
    this.getStoreNewCollection()
  }
  scrollToTop() {
    this.container.nativeElement.scrollIntoView(true);
  }
  getStoreNewCollection() {
    this.store$ = this.storeService.getNewCollection()
    this.store$.subscribe((e) => {
      this.vetor = e
    })

  }

  addProductToCart(_id: number, name: string, price: number, size: string, quant: number, total: number) {
    const products: Cart = { _id, name, price, size, quant, total }
    this.cartService.addToCart(products)

  }
}
