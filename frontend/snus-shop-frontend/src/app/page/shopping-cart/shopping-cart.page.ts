import { Component, OnInit } from '@angular/core';
import {IonicModule} from '@ionic/angular';
import {HeaderComponent} from '../../component/header/header.component';

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.page.html',
  styleUrls: ['./shopping-cart.page.scss'],
  imports: [
    IonicModule,
    HeaderComponent
  ]
})
export class ShoppingCartPage implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
