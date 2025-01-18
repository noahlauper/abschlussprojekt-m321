import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'shoppingCart',
  standalone: true
})
export class ShoppingCartPipe implements PipeTransform {

  transform(value: unknown, ...args: unknown[]): unknown {
    return null;
  }

}
