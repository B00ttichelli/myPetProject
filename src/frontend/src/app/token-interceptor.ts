import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {AuthService} from "./service/auth.service";

@Injectable({
  providedIn: 'root'
})
export class TokenInterceptor implements HttpInterceptor{


  constructor(public authService:AuthService) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let jwt =  this.authService.getJwtToken()
    if(jwt!=null){
      return next.handle(this.addToken(req,jwt))
    }

    return next.handle(req);
  }

    addToken(req: HttpRequest<any>, jwt: any){
     return req.clone({
       setHeaders:{
         Authorization: 'Bearer '+jwt
       }
     })

    }


}


/*({
       headers: req.headers.set('Authorization','Bearer '+jwt)
     });*/
