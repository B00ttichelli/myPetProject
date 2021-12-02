import { Component, OnInit } from '@angular/core';
import {ForumModel} from "../../shared/forumModel";
import {ForumService} from "../service/forum.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  forums$: Array<ForumModel> = []

  constructor(private forumService:ForumService) {
    this.forumService.getAllForums().subscribe(forum=>{
      this.forums$=forum;
    })
  }

  ngOnInit(): void {
  }

}
