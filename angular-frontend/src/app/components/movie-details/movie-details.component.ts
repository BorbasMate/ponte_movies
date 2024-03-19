import {Component} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {MovieListItemModel} from "../../models/movie-list-item.model";
import {RatingListItemModel} from "../../models/rating-list-item.model";
import {MovieService} from "../../services/movie.service";
import {RatingService} from "../../services/rating.service";
import {validationHandler} from "../utils/validationHandler";

@Component({
    selector: 'app-movie-details',
    templateUrl: './movie-details.component.html',
    styleUrls: ['./movie-details.component.css']
})
export class MovieDetailsComponent  {

    ratingForm!: FormGroup;
    movieId!: number;
    movieListItem!: MovieListItemModel;
    ratingListItems!: RatingListItemModel[];
    errorMessage: string = '';
    imageIndex: number = 0;
    imageMaxIndex!: number;
    existingImageListItemFileUrls!: string[];
    pictureUrl!: string;


    constructor(private formBuilder: FormBuilder,
                private movieService: MovieService,
                private ratingService: RatingService) {
        // @ts-ignore
        this.movieListItem = JSON.parse(sessionStorage.getItem('selectedMovie'));
        console.log(this.movieListItem.title);
        this.movieId = this.movieListItem.movieId;
        this.getImages();
        this.getRatings();
        this.imageIndex = 0;
        this.imageMaxIndex = this.movieListItem.images.length - 1;
        console.log(this.imageMaxIndex);

        this.ratingForm = this.formBuilder.group({
            'movieId': [this.movieId],
            'ratingValue': ['', Validators.required],
            'text': [''],
            'email': ['', [this.customEmailValidator, Validators.required]]
        });
    }


    submit = () => {
        console.log(this.ratingForm.value);
        this.ratingService.createNewRating(this.ratingForm.value).subscribe({
            next: () => location.reload(),
            error: error => validationHandler(error, this.ratingForm),
            complete: () => {
            }
        })
    }

    setRating(rating: number) {
        this.ratingForm.controls['ratingValue'].setValue(rating);
    }

    private getRatings(): void {
        this.ratingService.getRatings(this.movieId).subscribe({
                next: (data: RatingListItemModel[]) => {
                    this.ratingListItems = data;
                    if (data.length === 0) {
                        this.errorMessage = "There is no rating for this product!"
                        console.info("INFO: " + this.errorMessage);
                    } else {
                        console.info("RATINGS: ", data);
                    }
                },
                error: (error) => {
                    console.warn(error);
                },
                complete: () => {
                }
            }
        )
    }

    private getImages() {
        this.existingImageListItemFileUrls = this.movieListItem.images
            .map(image => "https://image.tmdb.org/t/p/w500" + image)
        this.pictureUrl = this.existingImageListItemFileUrls[this.imageIndex];

        if (this.movieListItem.images.length > 1) {
            this.imageMaxIndex = this.movieListItem.images.length - 1;
        }
        console.info("IMAGES: ", this.movieListItem.images);

    }


    customEmailValidator(control: FormControl): { isInValid: boolean } | null {
        let validationResult: any = null;
        if (control.value) {
            const selectedEmail: string = control.value;
            const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

            if (!(emailRegex.test(selectedEmail))) {
                validationResult = {isInValid: true};
            }
        }
        return validationResult;
    }

    decreaseIndex() {
        this.imageIndex--;
        this.pictureUrl = this.existingImageListItemFileUrls[this.imageIndex];
        console.log(this.existingImageListItemFileUrls[this.imageIndex]);
    }

    increaseIndex() {
        this.imageIndex++;
        this.pictureUrl = this.existingImageListItemFileUrls[this.imageIndex];
        console.log(this.existingImageListItemFileUrls[this.imageIndex]);
    }

}
