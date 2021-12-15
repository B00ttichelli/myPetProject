import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {PostModel} from "../../shared/postModel";


@Injectable({
  providedIn: 'root'
})
export class PostsService {

  constructor(private httpClient:HttpClient) { }

  getAllPosts(id):Observable<Array<PostModel>> {
    return this.httpClient.get<Array<PostModel>>("http://localhost:8080/api/post/get?id="+id);
  }
}
