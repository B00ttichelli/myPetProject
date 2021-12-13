import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {catchError, Observable, throwError} from "rxjs";
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
      req = this.addToken(req,jwt);
    }

    return next.handle(req).pipe(
      catchError((error: HttpErrorResponse) => {

        if (error.status === 401) {
          return this.handleAuthError(req, next);
        }
        return throwError(error);
      })
    );
  }

    addToken(req: HttpRequest<any>, jwt: string){
    console.log(jwt);
     return req.clone({
       setHeaders:{
         Authorization: 'Bearer '+jwt
       }
     })

    }

    handleAuthError(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>>{
      this.authService.getRefreshToken();
      return next.handle(this.addToken(req,this.authService.getJwtToken()));
    }

}


