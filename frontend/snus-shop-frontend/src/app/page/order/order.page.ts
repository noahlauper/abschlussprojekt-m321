import { Component, OnInit } from '@angular/core';
import {HeaderComponent} from '../../component/header/header.component';
import {IonicModule} from '@ionic/angular';
import {ToastService} from '../../services/toast/toast.service';
import {OrderService} from '../../services/order/order.service';
import {OrderResponse} from '../../model/OrderResponse';

@Component({
  selector: 'app-order',
  templateUrl: './order.page.html',
  styleUrls: ['./order.page.scss'],
  imports: [
    HeaderComponent,
    IonicModule
  ]
})
export class OrderPage implements OnInit {

  orderResponseList: OrderResponse[];

  constructor(
    private toastService: ToastService,
    private orderService: OrderService
  ) {
    this.orderResponseList = [];
  }

  ngOnInit() {
    this.getOrders();
  }

  getOrders() {
    this.orderService.getOrders().subscribe({
      next: (orderResposneList) => {
        this.orderResponseList = orderResposneList;
      }
    })
  }

}
