import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { ShoppingCartPageRoutingModule } from './shopping-cart-routing.module';

import { ShoppingCartPage } from './shopping-cart.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule.forRoot(),
    ShoppingCartPageRoutingModule,
    ShoppingCartPage,
  ],
  declarations: []
})
export class ShoppingCartPageModule {}
