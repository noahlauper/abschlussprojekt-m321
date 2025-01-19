import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';
import {AuthGuard} from './guard/auth.guard';

const routes: Routes = [
  {
    path: 'home',
    loadChildren: () => import('./page/home/home.module').then( m => m.HomePageModule),
  },
  {
    path: 'register',
    redirectTo: 'home',
    pathMatch: 'full',
  },
  {
    path: 'shopping-cart',
    loadChildren: () => import('./page/shopping-cart/shopping-cart.module').then( m => m.ShoppingCartPageModule),
  },
  {
    path: 'login',
    loadChildren: () => import('./page/login/login.module').then( m => m.LoginPageModule)
  },
  {
    path: 'register',
    loadChildren: () => import('./page/register/register.module').then( m => m.RegisterPageModule)
  },
  {
    path: 'order',
    loadChildren: () => import('./page/order/order.module').then( m => m.OrderPageModule)
  },
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
