import { Component, OnInit } from '@angular/core';
import {IonicModule} from '@ionic/angular';
import {HeaderComponent} from '../../component/header/header.component';
import {ProductCardComponent} from '../../component/product-card/product-card.component';
import {Product} from '../../model/Product';
import {ShoppingCartService} from '../../services/shopping-cart/shopping-cart.service';
import {ReactiveFormsModule} from '@angular/forms';

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

  constructor(
    private cartService: ShoppingCartService
  ) {
    this.products = [];
    this.finishOrder = false;
    this.sum = 0;
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
}
