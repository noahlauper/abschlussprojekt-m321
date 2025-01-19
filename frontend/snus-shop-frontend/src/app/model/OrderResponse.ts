import {Product} from './Product';

export class OrderResponse {
  paymentMethod: string;
  products: Product[];


  constructor() {
    this.paymentMethod = '';
    this.products = [];
  }
}
