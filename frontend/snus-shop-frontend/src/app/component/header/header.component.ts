import { Component, OnInit } from '@angular/core';
import {IonicModule} from '@ionic/angular';
import {Router} from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
  imports: [
    IonicModule
  ]
})
export class HeaderComponent  implements OnInit {

  constructor(
    private router: Router,
  ) { }

  ngOnInit() {}


  redirectToCart() {
    this.router.navigate(['shopping-cart'], {})
  }

  redirectToHome() {
    this.router.navigate(['home'], {})
  }

}
