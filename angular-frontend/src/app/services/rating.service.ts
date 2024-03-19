import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {RatingCreationCommandModel} from "../models/rating-creation-command.model";
import {Observable} from "rxjs";
import {RatingListItemModel} from "../models/rating-list-item.model";

@Injectable({
  providedIn: 'root'
})
export class RatingService {

  baseUrl = environment.BASE_URL + '/api/ratings';

  constructor(private httpClient: HttpClient) { }

  createNewRating(ratingCreationCommand: RatingCreationCommandModel): Observable<any> {
    return this.httpClient.post<any>(`${this.baseUrl}`, ratingCreationCommand);
  }

  getRatings(movieId: number): Observable<Array<RatingListItemModel>> {
    return this.httpClient.get<Array<RatingListItemModel>>(`${this.baseUrl}/${movieId}`)
  }


}
