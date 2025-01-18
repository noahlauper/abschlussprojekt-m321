import {Component, Input, OnInit} from '@angular/core';
import {IonicModule} from '@ionic/angular';
import {Product} from '../../model/Product';
import {ShoppingCartService} from '../../services/shopping-cart/shopping-cart.service';
import {ShoppingCartResponse} from '../../model/ShoppingCartResponse';
import {ToastService} from '../../services/toast/toast.service';

@Component({
  selector: 'app-product-card',
  templateUrl: './product-card.component.html',
  styleUrls: ['./product-card.component.scss'],
  imports: [
    IonicModule
  ]
})
export class ProductCardComponent  implements OnInit {
  @Input() product: Product;
  @Input() isInCart: boolean;

  constructor(
    private cartService: ShoppingCartService,
    private toastService: ToastService
  ) {
    this.product = new Product();
    this.isInCart = false;
  }

  ngOnInit() {}

  addToCart() {
    this.cartService.addProduct(this.product.id).subscribe({
      next: (productIds: ShoppingCartResponse) => {
        this.toastService.createToast('Produkt zum Warenkorb hinzugefügt', 'success', 2000);
      },
      error: err => {
        this.toastService.createToast('Produkt konnte nicht zum Warenkorb hinzugefügt werden', 'warning', 2000);
      }
    })
  }

  removeFromCart() {
    this.cartService.removeProduct(this.product.id).subscribe({
      next: (productIds: ShoppingCartResponse) => {
        this.toastService.createToast('Produkt aus Warenkorb entfernt', 'success', 2000);
      },
      error: err => {
        this.toastService.createToast('Produkt konnte aus dem Warenkorb entfernt werden', 'warning', 2000);
      }
    })
  }
}
