import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {CommentModel} from "../../shared/commentModel";

@Injectable({
  providedIn: 'root'
})

export class CommentService {
  constructor(private http:HttpClient) {
  }

  getAllCommentsByPostId(id):Observable<Array<CommentModel>>{
    return this.http.get<Array<CommentModel>>("http://localhost:8080/api/comment/get?id="+id);

  }
}
