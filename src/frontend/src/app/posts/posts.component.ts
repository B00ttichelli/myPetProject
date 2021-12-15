import { Component, OnInit } from '@angular/core';
import {PostModel} from "../../shared/postModel";
import {PostsService} from "../service/posts.service";
import {ActivatedRoute} from "@angular/router";


@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.css']
})
export class PostsComponent implements OnInit {

  posts$: Array<PostModel> = []

  constructor(private postsService: PostsService, private route: ActivatedRoute) {
    this.route.params.subscribe(params=>{this.postsService.getAllPosts(params['id']).subscribe(data=>
    {this.posts$ = data})})
  }

  ngOnInit(): void {
  }

}
