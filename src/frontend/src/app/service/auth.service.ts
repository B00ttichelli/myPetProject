import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {RegisterRequestPayload} from "../register/register-request.payload";
import {map, Observable} from "rxjs";
import {LoginRequestPayload} from "../login/loginRequest.payload";
import {LoginResponsePayload} from "../login/loginResponse.payload";
import {LocalStorageService} from "ngx-webstorage";
import {FreshTokenPair} from "../../shared/freshTokenPair";


@Injectable({
  providedIn: 'root'
})
export class AuthService {


  constructor(private httpClient: HttpClient,private localStorage:LocalStorageService) {


  }

  signup(registerRequestPayLoad:RegisterRequestPayload): Observable<any> {
     return this.httpClient.post('http://localhost:8080/api/auth/signup'
       ,registerRequestPayLoad, {responseType:'text'});
  }

  login(loginRequestPayload: LoginRequestPayload):Observable<boolean>{
     return this.httpClient.post<LoginResponsePayload>('http://localhost:8080/api/auth/signIn',loginRequestPayload)
      .pipe(map(data=>{
        this.localStorage.store('authenticationToken',data.accessToken);
        this.localStorage.store('username',data.name);
        this.localStorage.store('refreshToken',data.refreshToken);
        this.localStorage.store('expiresAt',data.expiresAt);
        return true;
      }))

  }


  getJwtToken(): string{
    return this.localStorage.retrieve('authenticationToken');
  }
  getRefreshToken(): string{
    return this.localStorage.retrieve('refreshToken');
  }

  getNewTokenPair(refreshToken: string){

    var stringObservable = this.httpClient.get<FreshTokenPair>("http://localhost:8080/api/auth/refreshToken?refreshToken="+refreshToken)
      .pipe(map(data=>{
        localStorage.removeItem('authenticationToken')
        localStorage.removeItem('refreshToken')
        this.localStorage.store('authenticationToken',data.accessToken);
        this.localStorage.store('refreshToken',data.refreshToken);
        /*return data.accessToken;*/
      }));

  }
}
