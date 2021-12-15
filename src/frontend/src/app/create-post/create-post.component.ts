import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {CreatePostPayload} from "../../shared/CreatePostPayload";
import {ActivatedRoute, Router} from "@angular/router";
import {PostsService} from "../service/posts.service";


@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.css']
})
export class CreatePostComponent implements OnInit {

  createPostForm: FormGroup;
  postPayload: CreatePostPayload;


  constructor(private router: Router,private postService:PostsService,private activeRoute:ActivatedRoute) {
    this.postPayload = {
      description: '',
      postBody: '',
      postName: '',
      subForumId: 0
    }
  }

  ngOnInit(): void {
    this.createPostForm = new FormGroup({
      postName: new FormControl('',Validators.required),
      description: new FormControl('',Validators.required),
      postBody: new FormControl('',Validators.required)
    })
  }

  createPost() {

     this.activeRoute.params.subscribe(params => {this.postPayload.subForumId = params['id']})
     this.postPayload.postName = this.createPostForm.get("postName").value;
     this.postPayload.description = this.createPostForm.get('description').value;
     this.postPayload.postBody = this.createPostForm.get('postBody').value;

     this.postService.publishPost(this.postPayload).subscribe()
     this.router.navigateByUrl('/'); // toDo redirect to created Post
  }

  discardPost() {
    this.router.navigateByUrl('/')
  }
}
