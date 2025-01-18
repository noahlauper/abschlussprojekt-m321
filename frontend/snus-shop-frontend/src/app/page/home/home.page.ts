import {Component, OnInit} from '@angular/core';
import {Product} from '../../model/Product';
import {ProductService} from '../../services/product/product.service';
import {ToastService} from '../../services/toast/toast.service';

@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
  standalone: false,
})
export class HomePage implements OnInit{

  products: Product[];

  constructor(
    private productService: ProductService,
    private toastService: ToastService,
  ) {
    this.products = [];
  }


  ngOnInit(): void {
    this.getProducts();
  }


  getProducts() {
    this.productService.getAllProducts().subscribe({
      next: (products: Product[]) =>  {
        this.products = products;
      },
      error: err => {
        this.toastService.createToast('Produkte konnten nicht geladen werden', 'danger', 2000);
      }
    })
  }
}
