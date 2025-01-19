import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {Product} from '../../model/Product';
import {HttpClient} from '@angular/common/http';
import {ShoppingCartResponse} from '../../model/ShoppingCartResponse';

@Injectable({
  providedIn: 'root'
})
export class ShoppingCartService {

  cartURL: string = 'http://localhost:8222/snus-shop/cart'


  constructor(
    private http: HttpClient
  ) { }

  getProductsInCart(): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.cartURL}`)
  }

  addProduct(productId: number): Observable<ShoppingCartResponse> {
    return this.http.put<ShoppingCartResponse>(`${this.cartURL}/add-product/${productId}`, null)
  }

  removeProduct(productId: number): Observable<ShoppingCartResponse> {
    return this.http.patch<ShoppingCartResponse>(`${this.cartURL}/remove-product/by-id/${productId}`, null);
  }
}
