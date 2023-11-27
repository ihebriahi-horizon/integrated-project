import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, map, Observable, tap, throwError } from 'rxjs';
import { AuthService } from 'src/app/auth/services/auth-service.service';

@Injectable({
  providedIn: 'root',
})
export class StoreServicesService {
  private readonly url = `http://${window.location.host}/api`;

  status!: string;
  errorMessage: any;

  constructor(
    private http: HttpClient,
    private authService: AuthService
  ) { }

  getProducts(page: number) {
    return this.http.get<any>(`${this.url}/products?page=${page}`);
  }

  getProduct(id: number) {
    return this.http.get<any>(`${this.url}/products/${id}`);
  }

  getNewCollection() {
    return this.http.get<PageResponse>(`${this.url}/products?size=15`);
  }

  searchProducts(
    gender: string,
    page: number,
    name: string,
    type: string,
    colors: string,
    minPrice: number,
    maxPrice: number
  ) {
    return this.http.get<PageResponse>(
      `${this.url}/products/search?page=${page}&size=6&gender=${gender}&name=${name}&type=${type}&colors=${colors}&minPrice=${minPrice}&maxPrice=${maxPrice}`
    );
  }

  addProduct(product: any): Observable<HttpResponse<any>> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Bearer ' + this.authService.getToken(),
      }),
    };
    return this.http
      .post<any>(`${this.url}/products`, product, {
        ...httpOptions,
        observe: 'response',
      })
      .pipe(
        map((response) => {
          return response;
        }),
        catchError((error) => {
          // For any other error, throw a generic error message
          return throwError({
            status: 'ERROR',
            message: 'An error occurred while adding the new product',
          });
        })
      );
  }

  updateProduct(id: number, product: any): Observable<HttpResponse<any>> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Bearer ' + this.authService.getToken(),
      }),
    };
    return this.http
      .put<any>(`${this.url}/products/${id}`, product, {
        ...httpOptions,
        observe: 'response',
      })
      .pipe(
        map((response) => {
          return response;
        }),
        catchError((error) => {
          // For any other error, throw a generic error message
          return throwError({
            status: 'ERROR',
            message: 'An error occurred while updating the product',
          });
        })
      );
  }

  deleteProduct(id: number): Observable<HttpResponse<any>> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Bearer ' + this.authService.getToken(),
      }),
    };
    return this.http
      .delete(`${this.url}/products/${id}`, {
        ...httpOptions,
        observe: 'response',
      })
      .pipe(
        map((response) => {
          return response;
        }),
        catchError((error) => {
          // For any other error, throw a generic error message
          return throwError({
            status: 'ERROR',
            message: 'An error occurred while deleting the product',
          });
        })
      );
  }

  getProductTypes() {
    return this.http.get<any>(`${this.url}/products/types`);
  }

  getProductColors() {
    return this.http.get<any>(`${this.url}/products/colors`);
  }

  getMinProductPrice() {
    return this.http.get<any>(`${this.url}/products/price/min`);
  }

  getMaxProductPrice() {
    return this.http.get<any>(`${this.url}/products/price/max`);
  }
}

export interface PageResponse {
  content: [];
  totalPages: number;
}
