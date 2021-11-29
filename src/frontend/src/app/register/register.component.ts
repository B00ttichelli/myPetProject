import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {RegisterRequestPayload} from "./register-request.payload";
import {AuthService} from "../service/auth.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  registrationControl: FormGroup | undefined;
  registerRequestPayLoad: RegisterRequestPayload;
  constructor(private registerService: AuthService) {

    this.registerRequestPayLoad = {
      username:'',
      password:''
    }


  }

  ngOnInit(): void {
  this.registrationControl = new FormGroup({
    username: new FormControl('username',Validators.required),
    password: new FormControl('passwrod',Validators.required)
  })

  }

  signup(){
    this.registerRequestPayLoad.username =  this.registrationControl.get('username').value;
    this.registerRequestPayLoad.password = this.registrationControl.get('password').value;

    this.registerService.signup(this.registerRequestPayLoad)
      .subscribe(data =>{console.log(data)});
  }

}
