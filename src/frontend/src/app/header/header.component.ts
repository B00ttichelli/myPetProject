import { Component, OnInit } from '@angular/core';
import {AuthService} from "../service/auth.service";
import {Router} from "@angular/router";
import {LocalStorageService} from "../service/localStorage.service";


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
    username: string;
    isLoggedIn: boolean;
  constructor(private authService: AuthService, private router:Router) { }

  ngOnInit(): void {
    this.isLoggedIn = this.authService.isLoggedIn();
    this.username  = this.authService.getUsername();

  }

  goToProfile(){
    this.router.navigateByUrl("/user-profile/"+this.username);
  }

  logout(){

    this.router.navigateByUrl("/logout").then(()=>window.localStorage.clear()).then(()=>window.location.reload);
    ;
  }

}
