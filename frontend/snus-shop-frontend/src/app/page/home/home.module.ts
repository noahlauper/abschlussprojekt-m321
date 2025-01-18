import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { IonicModule } from '@ionic/angular';
import { FormsModule } from '@angular/forms';
import { HomePage } from './home.page';

import { HomePageRoutingModule } from './home-routing.module';
import {HeaderComponent} from '../../component/header/header.component';
import {ProductCardComponent} from '../../component/product-card/product-card.component';


@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    HomePageRoutingModule,
    HeaderComponent,
    ProductCardComponent
  ],
  declarations: [HomePage]
})
export class HomePageModule {}
