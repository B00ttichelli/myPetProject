import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {LoginRequestPayload} from "./loginRequest.payload";
import {AuthService} from "../service/auth.service";
import {ActivatedRoute, Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  loginRequestPayload:LoginRequestPayload;
  isError: boolean;

  constructor(private authService:AuthService,
              private router:Router,
              private  toAstra: ToastrService,
              private activatedRoute: ActivatedRoute) {
    this.loginRequestPayload = {
      username:'',
      password:''
    }
  }

  ngOnInit(): void {

    this.loginForm = new FormGroup({
      username: new FormControl('username',Validators.required),
      password: new  FormControl('password',Validators.required)
    });

    this.activatedRoute.queryParams
      .subscribe(params=>{
        if (params['registred'] !== undefined && params['registred'] === 'true'){
          this.toAstra.success("Signup succesfull");
        }
      });

  }

  login(){
    this.loginRequestPayload.username = this.loginForm.get('username').value;
    this.loginRequestPayload.password = this.loginForm.get('password').value;
    this.authService.login(this.loginRequestPayload).subscribe(data=>{
      if(data){
        this.isError = true;
        this.router.navigateByUrl('/');
        this.toAstra.success("Login Successful")
      }else {
        this.isError =false;
      }
    })



  }

}
