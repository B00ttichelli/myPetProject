import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {RegisterRequestPayload} from "./register-request.payload";
import {AuthService} from "../service/auth.service";
import {Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  registrationControl: FormGroup ;
  registerRequestPayLoad: RegisterRequestPayload;
  constructor(private registerService: AuthService,private router: Router, private toAstra: ToastrService) {

    this.registerRequestPayLoad = {
      username:'',
      password:''
    }


  }

  ngOnInit(): void {
  this.registrationControl = new FormGroup({
    username: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required)
  })

  }

  signup(){
    this.registerRequestPayLoad.username =  this.registrationControl.get('username').value;
    this.registerRequestPayLoad.password = this.registrationControl.get('password').value;

    this.registerService.signup(this.registerRequestPayLoad)
      .subscribe(()=>{this.router.navigate(['/login'], {queryParams:{ registred:'true'}});},
        ()=>{this.toAstra.error('Registration Failed ')});
  }

}
