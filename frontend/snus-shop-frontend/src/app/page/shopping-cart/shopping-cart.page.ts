import { Component, OnInit } from '@angular/core';
import {IonicModule} from '@ionic/angular';
import {HeaderComponent} from '../../component/header/header.component';
import {ProductCardComponent} from '../../component/product-card/product-card.component';
import {Product} from '../../model/Product';
import {ShoppingCartService} from '../../services/shopping-cart/shopping-cart.service';
import {ReactiveFormsModule} from '@angular/forms';
import {OrderService} from '../../services/order/order.service';
import {ToastService} from '../../services/toast/toast.service';

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.page.html',
  styleUrls: ['./shopping-cart.page.scss'],
  imports: [
    IonicModule,
    HeaderComponent,
    ProductCardComponent,
    ReactiveFormsModule
  ]
})
export class ShoppingCartPage implements OnInit {
  products: Product[];
  finishOrder: boolean;
  sum: number;
  paymentMethod: string;

  constructor(
    private cartService: ShoppingCartService,
    private orderService: OrderService,
    private toastService: ToastService

  ) {
    this.products = [];
    this.finishOrder = false;
    this.sum = 0;
    this.paymentMethod = '';
  }

  ngOnInit() {
    this.getProductsInCart();
  }

  getProductsInCart() {
    this.cartService.getProductsInCart().subscribe({
      next: (products: Product[]) => {
        this.products = products;
        for (let product of products) {
          this.sum += product.price;
        }
      }
    })
  }

  wantsToFinishOrder() {
    this.finishOrder = true;
  }

  createOrder() {
    let productIds: number[] =[];
    for (let product of this.products) {
      productIds.push(product.id);
    }
    this.orderService.createOrder(productIds, this.paymentMethod).subscribe({
      next: value => {
        this.toastService.createToast('Bestellung abgeschickt!', 'success', 2000);
      }
    })
  }

  setPaymentMethod(event: CustomEvent) {
    this.paymentMethod = event.detail.value;
  }
}
