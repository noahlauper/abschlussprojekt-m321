import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {Product} from '../../model/Product';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  productURL: string = 'http://localhost:8222/snus-shop/products'

  constructor(
    private http: HttpClient
  ) { }

  getAllProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.productURL}`)
  }
}
