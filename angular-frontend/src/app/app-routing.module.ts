import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MovieListComponent} from "./components/movie-list/movie-list.component";
import {MovieDetailsComponent} from "./components/movie-details/movie-details.component";
import {NavErrorComponent} from "./components/nav-error/nav-error.component";

const routes: Routes = [
  {path: "", component: MovieListComponent},
  {path: "product-list", component: MovieListComponent},
  {path: "movie-details", component: MovieDetailsComponent},
  {path: '*', redirectTo: 'product-list', pathMatch: 'full'},
  {path: '**', component: NavErrorComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
