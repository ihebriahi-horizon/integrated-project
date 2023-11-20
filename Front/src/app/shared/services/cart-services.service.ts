import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Cart } from '../interfaces/cart';

@Injectable({
  providedIn: 'root',
})
export class CartServicesService {
  private readonly url = 'http://localhost:3000';
  private cartData: Cart[] = [];

  constructor(private http: HttpClient) {}

  addToCart(product: Cart): void {
    if (product?.size >= 0) throw new Error('product quantity is undefined!');

    const existingProduct = this.cartData.find((p) => p._id === product._id);
    if (existingProduct == undefined || existingProduct.size === product.size) {
      this.cartData.push(product);
      return;
    }

    existingProduct.quant += product.quant;
    existingProduct.total += product.total;
  }

  getCart() {
    return this.cartData;
  }

  deleteFromCart(product: Cart) {
    this.cartData = this.cartData.filter((i) => i._id !== product._id);
  }

  cartProductsNumber(): number {
    return this.cartData.length;
  }
}
