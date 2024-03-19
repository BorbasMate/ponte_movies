import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable, Subject} from "rxjs";
import {PaginationDataModel} from "../models/pagination-data.model";
import {MovieListItemModel} from "../models/movie-list-item.model";

@Injectable({
  providedIn: 'root'
})
export class MovieService {

  baseUrl: string = environment.BASE_URL + '/api/movies';

  constructor(private httpClient: HttpClient) { }

  movieListItemSelected = new Subject<MovieListItemModel>();


  getMovies(page: number): Observable<PaginationDataModel> {
    return this.httpClient.get<PaginationDataModel>(`${this.baseUrl}?page=${page}`);
  }

}
