import {Component, OnInit} from '@angular/core';
import {MovieService} from "../../services/movie.service";
import {Router} from "@angular/router";
import {FormBuilder} from "@angular/forms";
import {PaginationDataModel} from "../../models/pagination-data.model";
import {MovieListItemModel} from "../../models/movie-list-item.model";

@Component({
  selector: 'app-movie-list',
  templateUrl: './movie-list.component.html',
  styleUrls: ['./movie-list.component.css']
})
export class MovieListComponent implements OnInit {

  movieListItems!: MovieListItemModel[];
  currentPage: number = 1;
  paginationLimit = 20;
  paginationTotal! : number; //initial value, no matter what

  constructor(private formBuilder: FormBuilder,
              private movieService: MovieService,
              private router: Router) {
    if (sessionStorage.getItem('currentPage')) {
      // @ts-ignore
      this.currentPage = JSON.parse(sessionStorage.getItem('currentPage'));
    }
  }

  ngOnInit() {
      this.getMovies();
  }

  details(movieId: number) {
    const selectedMovie = this.movieListItems
        .find(movie => movie.movieId === movieId);

    sessionStorage.setItem('selectedMovie', JSON.stringify(selectedMovie));
    this.router.navigate(['/movie-details']);

  }

  private getMovies() {
    this.movieService.getMovies(this.currentPage).subscribe({
      next: (data: PaginationDataModel) => {
        this.movieListItems = data.movieListItemList
        this.paginationTotal = data.total
        console.log("total movies: " + this.paginationTotal);
      },
      error: (error) => console.warn(error),
      complete: () => {
        sessionStorage.setItem('currentPage', JSON.stringify(this.currentPage));
      }
    });
  }

  changePage(page: number): void {
    console.log(page);
    this.currentPage = page;
    this.getMovies();
  }

}
