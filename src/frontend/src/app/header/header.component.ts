import { Component, OnInit } from '@angular/core';
import {AuthService} from "../service/auth.service";
import {ActivatedRoute, Router} from "@angular/router";




@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
    username: string;
    isLoggedIn: boolean;
    isCanPost:boolean;
  constructor(private authService: AuthService, private router:Router, private activeRouter:ActivatedRoute ) { }

  ngOnInit(): void {
    this.isLoggedIn = this.authService.isLoggedIn();
    this.username  = this.authService.getUsername();


  }

  goToProfile(){
    this.router.navigateByUrl("/user-profile/"+this.username).then(()=>window.location.reload());
  }

  logout(){

    this.router.navigateByUrl("/logout").then(()=>window.localStorage.clear()).then(()=>window.location.reload);

  }
   goHomeYankee(){
    this.router.navigateByUrl("/").then(()=>window.location.reload());
   }

  goCreatePost(){
    this.activeRouter.params.subscribe(param=>{this.router.navigateByUrl('/create-post/'+param['id'])})
  }




}
