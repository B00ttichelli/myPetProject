import {BehaviorSubject} from "rxjs";
import {Injectable} from "@angular/core";


@Injectable({
  providedIn: 'root',
})



export  class LocalStorageService{
  private readonly JWT =  'authenticationToken';
  private readonly REFRESHTOKEN = 'refreshToken';
  private readonly EXP = 'expiresAt';
  private readonly USERNAME = 'username';

  accessTokenBehaviorSubject: BehaviorSubject<string> = new BehaviorSubject<string>(this.getAccessToken());
  usernameBehaviorSubject: BehaviorSubject<string> = new BehaviorSubject<string>(this.getUsername());
  refreshTokenBehaviorSubject: BehaviorSubject<string> = new BehaviorSubject<string>(this.getRefreshToken());
  expDateBehaviorSubject: BehaviorSubject<string> = new BehaviorSubject<string>(this.getExpireDate())


  public getAccessToken(): string {
    return localStorage.getItem(this.JWT)
  }
  public getUsername():string{
    return localStorage.getItem(this.EXP)
  }

  public getRefreshToken(): string {
    return localStorage.getItem(this.REFRESHTOKEN)
  }

  public getExpireDate(): string {
    return localStorage.getItem(this.EXP)
  }

  public setAccessToken( accessToken:string){
    localStorage.setItem(this.JWT,accessToken);
    this.accessTokenBehaviorSubject.next(accessToken)
  }

  public setRefreshToken(refreshToken:string){
    localStorage.setItem(this.REFRESHTOKEN,refreshToken);
    this.refreshTokenBehaviorSubject.next(refreshToken);
  }

  public setUsername(userName: string){
    localStorage.setItem(this.USERNAME,userName);
    this.usernameBehaviorSubject.next(userName);
  }

  public setExpDate(expDate:string){
    localStorage.setItem(this.EXP,expDate);
    this.expDateBehaviorSubject.next(expDate);
  }

}
