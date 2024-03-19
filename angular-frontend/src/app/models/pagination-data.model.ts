import {MovieListItemModel} from "./movie-list-item.model";

export interface PaginationDataModel {

  movieListItemList: MovieListItemModel[];
  total: number;

}
