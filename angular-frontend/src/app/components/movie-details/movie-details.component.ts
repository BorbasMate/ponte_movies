import {Component, OnDestroy, OnInit} from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, ValidatorFn, Validators} from "@angular/forms";
import {MovieListItemModel} from "../../models/movie-list-item.model";
import {RatingListItemModel} from "../../models/rating-list-item.model";
import {MovieService} from "../../services/movie.service";
import {RatingService} from "../../services/rating.service";
import {ActivatedRoute, Router} from "@angular/router";
import {validationHandler} from "../utils/validationHandler";

@Component({
    selector: 'app-movie-details',
    templateUrl: './movie-details.component.html',
    styleUrls: ['./movie-details.component.css']
})
export class MovieDetailsComponent implements OnInit {

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
                private ratingService: RatingService,
                private route: ActivatedRoute,
                private router: Router) {
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
            'ratingValue': ['', [Validators.required, this.ratingValidator()]],
            'text': [''],
            'email': ['', Validators.required]
        });

    }

    ngOnInit() {


        // this.movieService.movieListItemSelected.subscribe({
        //     next: (value) => {
        //         this.movieListItem = value;
        //         console.log(this.movieListItem.title);
        //         this.movieId = value.movieId;
        //         this.getImages();
        //         this.getRatings();
        //         this.imageIndex = 0;
        //         this.imageMaxIndex = value.images.length - 1;
        //         console.log(this.imageMaxIndex);
        //
        //         this.ratingForm = this.formBuilder.group({
        //             'movieId': [this.movieId],
        //             'ratingValue': ['', [Validators.required, this.ratingValidator()]],
        //             'text': [''],
        //             'email': ['', Validators.required]
        //         });
        //
        //     }
        // })


        // this.route.paramMap.subscribe(
        //     paramMap => {
        //       const productId: number = Number(paramMap.get('id'));
        //       if (productId) {
        //         this.productId = productId;
        //         console.log("This product has the id: " + this.productId)
        //         this.getProductDetailsById();
        //         this.getRatings();
        //         this.getImages();
        //         this.imageIndex = 0;
        //
        //         this.ratingForm = this.formBuilder.group({
        //           'ratingValue': ['', [Validators.required, this.ratingValidator()]],
        //           'text': [''],
        //           'customUserName': [this.getUsername()],
        //           'productId': [this.productId]
        //         });
        //       }
        //     }
        // )
    }

    // ngOnDestroy() {
    //     if (this.existingImageListItemFileUrls) {
    //         this.existingImageListItemFileUrls
    //             .forEach((fileUrl) => URL.revokeObjectURL(fileUrl))
    //     }
    // }


    // private getProductDetailsById() {
    //   this.productService.getProductById(this.productId).subscribe({
    //     next: (data: ProductListItemModel) => {
    //       this.productListItem = data;
    //       this.pictureUrl = this.productListItem.pictureUrl;
    //     },
    //     error: (error) => console.warn(error),
    //     complete: () => {
    //       if (this.productListItem === null) {
    //         this.router.navigate(['**'])
    //             .finally(() => {
    //               console.warn("There is no product with that id!")
    //             });
    //       } else {
    //         console.log("Loaded item: ", this.productListItem);
    //       }
    //     }
    //   });
    // }

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

    // isLoggedIn() {
    //   return sessionStorage.getItem('user');
    // }

    // getUsername() {
    //   if (sessionStorage.getItem("user") != null) {
    //     let userData = sessionStorage.getItem('user');
    //     let userObject = JSON.parse(userData);
    //     return userObject.username;
    //   }
    // }

    private ratingValidator(): ValidatorFn {
        return (control: AbstractControl): { [key: string]: any } | null => {
            const value = control.value;
            if (control.touched && !value) {
                return {'noSelection': true};
            }
            return null;
        };
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

    // private getImages(): void {
    //
    //     const fileObservables = this.movieListItem.images.map((element) =>
    //         this.convertImageToFile(element).pipe(
    //             map((file) => ({image: file})))
    //     );
    //
    //       forkJoin(fileObservables).subscribe((imageListItemFiles) => {
    //         this.existingImageListItemFileUrls = imageListItemFiles
    //             .map((item) => URL.createObjectURL(item.image));
    //         this.pictureUrl = this.existingImageListItemFileUrls[this.imageIndex];
    //         console.log(this.existingImageListItemFileUrls[this.imageIndex]);
    //       });
    //
    //
    //     if (this.movieListItem.images.length > 1) {
    //       this.imageMaxIndex = this.movieListItem.images.length - 1;
    //     }
    //     console.info("IMAGES: ", this.movieListItem.images);
    //
    // }

    // private getImages(): void {
    //   this.imageService.getImagesOfProduct(this.productId).subscribe({
    //         next: (data: ImageListItemModel[]) => {
    //           if (data) {
    //             const fileObservables = data.map((element) =>
    //                 this.convertImageListItemToFile(element).pipe(
    //                     map((file) => ({ id: element.id, image: file })))
    //             );
    //
    //             forkJoin(fileObservables).subscribe((imageListItemFiles) => {
    //               this.existingImageListItemFileUrls = imageListItemFiles
    //                   .map((item) => URL.createObjectURL(item.image));
    //               this.pictureUrl = this.existingImageListItemFileUrls[this.imageIndex];
    //               console.log(this.existingImageListItemFileUrls[this.imageIndex]);
    //             });
    //
    //           }
    //
    //           if (data.length > 1) {
    //             this.imageMaxIndex = data.length - 1;
    //           }
    //           console.info("IMAGES: ", data);
    //         },
    //         error: (error) => {
    //           console.warn(error);
    //         },
    //         complete: () => {
    //         }
    //       }
    //   )
    // }

    // private convertImageToFile(image: string): Observable<File> {
    //     return from(fetch('https://image.tmdb.org/t/p/w500' + image)
    //         .then(response => response.blob())
    //         .then(blob => new File([blob], image, {type: blob.type})));
    // }


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
