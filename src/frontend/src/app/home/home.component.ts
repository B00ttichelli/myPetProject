import { Component, OnInit } from '@angular/core';
import {ForumModel} from "../../shared/forumModel";
import {ForumService} from "../service/forum.service";
import {faComments} from '@fortawesome/free-solid-svg-icons'


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {


  faComments = faComments;
  forums$: Array<ForumModel> = []

  constructor(private forumService:ForumService) {
    this.forumService.getAllForums().subscribe(forum=>{
      this.forums$=forum;
    })
  }

  ngOnInit(): void {
  }

}
