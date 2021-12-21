import { Component, OnInit } from '@angular/core';
import {CommentModel} from "../../shared/commentModel";
import {PostsService} from "../service/posts.service";
import {CommentService} from "../service/comment.service";
import {ActivatedRoute} from "@angular/router";
import {PostModel} from "../../shared/postModel";

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.css']
})
export class CommentsComponent implements OnInit {
  post: PostModel;
  comments: Array<CommentModel> = [];

  constructor(private postsService:PostsService,
              private commentsService:CommentService,
              private route:ActivatedRoute,
              ) {



    this.route.params.subscribe(params=>{
      this.commentsService.getAllCommentsByPostId(params['id'])
        .subscribe(data=>{this.comments = data});
      this.postsService.getPostById(params['id']).subscribe(post=>{this.post = post});
    })
  }

  ngOnInit(): void {

  }

}
