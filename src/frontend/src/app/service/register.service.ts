import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {RegisterRequestPayload} from "../register/register-request.payload";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(private httpClient: HttpClient) {


  }

  signup(registerRequestPayLoad:RegisterRequestPayload): Observable<any> {
     return this.httpClient.post('http://localhost:8080/api/auth/signup'
       ,registerRequestPayLoad, {responseType:'text'});
  }
}
