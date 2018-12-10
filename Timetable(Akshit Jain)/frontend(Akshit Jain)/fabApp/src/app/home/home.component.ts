import { Component, OnInit } from '@angular/core';
import { Constants } from './../constants';
import { Router } from '@angular/router';
import { LoginService } from './../login.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  routes = [{}];
  activeRoute = '';
  constructor(private router: Router,private constant: Constants, private loginservice: LoginService) {}
  ngOnInit() {
    this.routes = this.constant.routes;
    console.log(this.router.url);
    if (this.router.url === '' || this.router.url === '/') {
      this.router.navigate([this.routes[0]['path']]);
    } else {
      this.router.navigate(['/' + this.router.url]);
    }
    this.loginservice.activeRoute.subscribe(res => {
      this.activeRoute = res;
    });

  }
  changeOfRoutes() {
    this.activeRoute = this.router.url;
    this.loginservice.changeActiveRoute();
  }
}
