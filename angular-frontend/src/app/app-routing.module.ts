import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MovieListComponent} from "./components/movie-list/movie-list.component";

const routes: Routes = [
  {path: "", component: MovieListComponent},
  {path: "product-list", component: MovieListComponent},
  {path: '*', redirectTo: 'product-list', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
