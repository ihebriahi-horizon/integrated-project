import { Component, Inject, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Cart } from 'src/app/shared/interfaces/cart';
import { CartServicesService } from 'src/app/shared/services/cart-services.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss'],
})
export class CartComponent implements OnInit {
  cartData: Cart[] = [];
  totalCompra: number = 0;
  quantidade: number = 0;
  totalFinal: number = 0;

  constructor(
    private cartService: CartServicesService,
    @Inject(MAT_DIALOG_DATA) public content: string
  ) {}

  ngOnInit(): void {
    this.getCart();
  }

  btnplus(_id: number) {
    let produto = this.cartData.find((p: any) => {
      return p._id === _id;
    });
    this.cartData = this.cartService.getCart();
    produto!.quant = produto!.quant + 1;
    this.quantidade = produto!.quant * produto!.price;
    produto!.total = this.quantidade;

    this.totalFinal = produto!.price + this.totalFinal;
  }

  btnmminus(_id: number) {
    let produto = this.cartData.find((p: any) => {
      return p._id === _id;
    });

    if (produto!.quant > 1) {
      produto!.quant = produto!.quant - 1;
      this.quantidade = produto!.quant * produto!.price;
      produto!.total = this.quantidade;

      this.totalFinal = this.totalFinal - produto!.price;
    }
  }

  getCart() {
    this.cartData = this.cartService.getCart();
    for (let i of this.cartData) {
      this.totalCompra = i.price + this.totalCompra;
      this.totalFinal = i.price + this.totalFinal;
      console.log(this.totalCompra);
    }
  }

  deleteProduct(
    _id: number,
    name: string,
    price: number,
    size: number,
    quant: number,
    total: number
  ) {
    const products: Cart = { _id, name, price, size, quant, total };

    this.cartData = this.cartData.filter((i: any) => i._id != _id);
    let produto = this.cartData.find((p: any) => {
      return p._id === _id;
    });
    this.cartService.deleteFromCart(products);
    this.totalFinal = this.totalFinal - produto!?.total;
  }
}
