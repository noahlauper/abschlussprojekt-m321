import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Product} from '../../model/Product';
import {OrderResponse} from '../../model/OrderResponse';

@Injectable({
  providedIn: 'root'
})
export class OrderService {


  orderURL: string = 'http://localhost:8222/snus-shop/orders'

  constructor(
    private http: HttpClient
  ) { }

  getOrders(): Observable<OrderResponse[]> {
    return this.http.get<OrderResponse[]>(`${this.orderURL}`)
  }

  createOrder(productIds: number[], paymentMethod: string): Observable<void> {
    return this.http.get<void>(`${this.orderURL}/${productIds}/${paymentMethod} `)
  }
}
