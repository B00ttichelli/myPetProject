import { Component, OnInit } from '@angular/core';
import {CommentModel} from "../../shared/commentModel";
import {PostsService} from "../service/posts.service";
import {CommentService} from "../service/comment.service";
import {ActivatedRoute, Router} from "@angular/router";
import {PostModel} from "../../shared/postModel";
import {CommentPayload} from "../../shared/commentPayload";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.css']
})
export class CommentsComponent implements OnInit {
  createCommentForm: FormGroup;
  commentPayload: CommentPayload;
  post: PostModel;
  comments: Array<CommentModel> = [];





  constructor(private postsService:PostsService,
              private commentsService:CommentService,
              private route:ActivatedRoute,
              private router:Router
              ) {

    this.commentPayload = {
      postId:0,
      text:''
    }

    this.route.params.subscribe(params=>{
      this.commentPayload.postId = params['id'];
      this.commentsService.getAllCommentsByPostId(params['id'])
        .subscribe(data=>{this.comments = data});
      this.postsService.getPostById(params['id']).subscribe(post=>{this.post = post});

    })
  }

  ngOnInit(): void {
      this.createCommentForm = new FormGroup({
        postBody:new FormControl('',Validators.required)
      })
  }

  createComment(){

    this.commentPayload.text = this.createCommentForm.get('postBody').value;

    this.commentsService.postComment(this.commentPayload).subscribe()

    this.router.navigateByUrl('/')

  }
  discardComment(){}


}
