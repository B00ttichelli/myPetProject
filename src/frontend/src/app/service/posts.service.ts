import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {PostModel} from "../../shared/postModel";
import {CreatePostPayload} from "../../shared/CreatePostPayload";


@Injectable({
  providedIn: 'root'
})
export class PostsService {

  constructor(private httpClient:HttpClient) { }

  getAllPosts(id):Observable<Array<PostModel>> {
    return this.httpClient.get<Array<PostModel>>("http://localhost:8080/api/post/get?id="+id);
  }

  publishPost(postPayload:CreatePostPayload){
    return this.httpClient.post('http://localhost:8080/api/post/create',postPayload);
  }
  getPostById(id):Observable<PostModel>{
    return this.httpClient.get<PostModel>("http://localhost:8080/api/post/getPost?id="+id);
  }
}
