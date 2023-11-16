import { Observable } from 'rxjs';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Cart } from 'src/app/shared/interfaces/cart';
import { CartServicesService } from 'src/app/shared/services/cart-services.service';
import { StoreServicesService } from 'src/app/shared/services/store-services.service';

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

  addProductToCart(_id: number, name: string, price: number, size: number, quant: number, total: number) {
    const products: Cart = { _id, name, price, size, quant, total }
    this.cartService.addToCart(products)

  }
}
