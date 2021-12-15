import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {RegisterComponent} from "./register/register.component";
import {HomeComponent} from "./home/home.component";
import {PostsComponent} from "./posts/posts.component";
import {CreatePostComponent} from "./create-post/create-post.component";

const routes: Routes = [
  {path:'',component:HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'forum/:id',component: PostsComponent},
  {path: 'create-post/:id',component: CreatePostComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes,{
    paramsInheritanceStrategy: 'always'
  })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
