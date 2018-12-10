import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { Router } from '@angular/router';


@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private router: Router) { }
  activeRoute = new BehaviorSubject<any>(this.router.url);
  changeActiveRoute() {
    this.activeRoute.next(this.router.url);
  }
}
