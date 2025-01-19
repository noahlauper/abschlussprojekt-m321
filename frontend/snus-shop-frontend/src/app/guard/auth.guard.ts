import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  Router,
  RouterStateSnapshot,
  UrlTree,
} from '@angular/router';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import {TokenValidation} from '../model/TokenValidation';

@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {
  constructor(private router: Router, private http: HttpClient) {}

  async canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Promise<boolean | UrlTree> {
    const token: string | null = localStorage.getItem('token');

    if (token !== null) {
      try {
        const result = await this.http
          .get<TokenValidation>('http://localhost:8222/snus-shop/auth/is-token-valid')
          .toPromise()
          .catch(function (err) {
            console.log(err)
          });
        if (result != undefined) {
          if (result.tokenValid) {
            return true;
          } else {
            return this.router.createUrlTree(['/register']);
          }
        }
      } catch (error) {
        console.log("there was an error in the request: ,", error)
        return this.router.createUrlTree(['/register']);
      }
    }
    console.log("token is undefined")
    return this.router.createUrlTree(['/register']);
  }
}
