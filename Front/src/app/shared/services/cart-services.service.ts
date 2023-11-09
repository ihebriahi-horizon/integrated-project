import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs';
import { Cart } from '../interfaces/cart';

@Injectable({
  providedIn: 'root'
})
export class CartServicesService {

  private readonly url = 'http://localhost:3000';
  private cartData: Cart[] = [];

  constructor(private http: HttpClient) { }

  addToCart(product: Cart) {

    if (product.size != "" && product.size!=null && product.size!=undefined) {
      const existingProduct = this.cartData.find(p => p._id === product._id);
      if (existingProduct) {
        if (existingProduct.size === product.size) {
          existingProduct.quant += product.quant;
          existingProduct.total += product.total;
        } else {
          this.cartData.push(product);
        }
      } else {
        this.cartData.push(product);
      }
    }
    
  }

  getCart() {
    return this.cartData;
  }

  deleteFromCart(product: Cart) {
    this.cartData = this.cartData.filter(i => i._id !== product._id);
  }

  cartProductsNumber(): number {
    return this.cartData.length;
  }

}
