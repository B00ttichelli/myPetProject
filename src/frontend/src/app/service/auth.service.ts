import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {RegisterRequestPayload} from "../register/register-request.payload";
import {map, Observable} from "rxjs";
import {LoginRequestPayload} from "../login/loginRequest.payload";
import {LoginResponsePayload} from "../login/loginResponse.payload";
import {LocalStorageService} from "./localStorage.service";




interface TokenPair {
  accessToken: string;
  refreshToken: string;
  username: string;
}

@Injectable({
  providedIn: 'root'
})


export class AuthService {


  constructor(public httpClient: HttpClient, public localStorageService: LocalStorageService) {


  }

  signup(registerRequestPayLoad:RegisterRequestPayload): Observable<any> {
     return this.httpClient.post('http://localhost:8080/api/auth/signup'
       ,registerRequestPayLoad, {responseType:'text'});
  }

  login(loginRequestPayload: LoginRequestPayload):Observable<boolean>{
     return this.httpClient.post<LoginResponsePayload>('http://localhost:8080/api/auth/signIn',loginRequestPayload)
      .pipe(map(data=>{
        this.localStorageService.setAccessToken(data.accessToken)
        this.localStorageService.setUsername(data.expiresAt)
        this.localStorageService.setRefreshToken(data.refreshToken);
        this.localStorageService.setExpDate(data.name);
        return true;
      }))

  }


  getJwtToken(): string{
    return this.localStorageService.getAccessToken();
  }
  getRefreshToken(): string{
    return this.localStorageService.getRefreshToken();
  }
  getUsername(): string{
    return this.localStorageService.getUsername();
  }

  getNewTokenPair(refreshToken: string){

/*    /!*let stringObservable = *!/this.httpClient.get<FreshTokenPair>("http://localhost:8080/api/auth/refreshToken?refreshToken="+refreshToken)
      .pipe(map(data=>{

        this.localStorage.store('authenticationToken',data.accessToken);
        this.localStorage.store('refreshToken',data.refreshToken);

        console.log(localStorage.getItem('authenticationToken'))

        return data.accessToken;
      }));*/
   /* console.log("Fuck")
    console.log(this.freshJwt)
    return this.freshJwt*/

    return this.httpClient.get<TokenPair>("http://localhost:8080/api/auth/refreshToken?refreshToken="+refreshToken).subscribe(data=>{
      this.localStorageService.setAccessToken(data.accessToken);
      this.localStorageService.setRefreshToken(data.refreshToken);
    })

  }
  isLoggedIn(): boolean {
    return this.getJwtToken() != null;
  }
}
