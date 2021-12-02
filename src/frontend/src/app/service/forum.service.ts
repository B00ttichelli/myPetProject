import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ForumModel} from "../../shared/forumModel";

@Injectable({
  providedIn: 'root'
})
export class ForumService {

  constructor(private httpClient:HttpClient) { }

  getAllForums():Observable<Array<ForumModel>>{
    return this.httpClient.get<Array<ForumModel>>("http://localhost:8080/api/forums");
  }
}
