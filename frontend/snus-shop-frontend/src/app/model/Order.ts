export class Order {
  id: number;
  paymentMethod: string
  productIds: number[];


  constructor() {
    this.id = 0;
    this.paymentMethod = '';
    this.productIds = [];
  }
}
