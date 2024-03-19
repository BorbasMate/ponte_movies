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

  // protected readonly document = document;

  movieListItems!: MovieListItemModel[];
  // productCartItems: ProductCartItemModel[] = [];
  // movieListItemSelected!: MovieListItemModel;
  // productCartItemToAdd!: ProductCartItemModel;
  currentPage: number = 1;
  paginationLimit = 20;
  paginationTotal = 50; //initial value, no matter what

  // productCategoryForm!: FormGroup;
  // mainCategories!: ProductCategoryListItemModel[];
  // subCategoriesFirst!: ProductCategoryListItemModel[];
  // subCategoriesSecond!: ProductCategoryListItemModel[];

  // productCategoryFilter: ProductCategoryFilterModel = {
  //   mainCategoriesSelected: [],
  //   subCategoriesFirstSelected: [],
  //   subCategoriesSecondSelected: []
  // };

  // isFiltered: boolean = JSON.parse(sessionStorage.getItem('isFiltered'));
  // isFirstFiltered: boolean = JSON.parse(sessionStorage.getItem('isFirstFiltered'));
  // isPaged: boolean = JSON.parse(sessionStorage.getItem('currentPage')) > 1;

  // filtersVisible: boolean = true;
  // userChangedFilterVisibility: boolean = false;

  constructor(private formBuilder: FormBuilder,
              private movieService: MovieService,
              private router: Router) {
    // this.productCategoryForm = this.formBuilder.group({
    //   mainCategoriesSelected: new FormArray([]),
    //   subCategoriesFirstSelected: new FormArray([]),
    //   subCategoriesSecondSelected: new FormArray([])
    // })
  }

  ngOnInit() {
    // this.initMobileLayout();
    // this.toggleFilterLayout();

    // window.onresize = () => {
    //   if (!this.userChangedFilterVisibility) {
    //     this.initMobileLayout();
    //     this.toggleFilterLayout();
    //   }
    // }

    // if (sessionStorage.getItem('mainCategoriesSelected')) {
    //   this.productCategoryFilter.mainCategoriesSelected =
    //     JSON.parse(sessionStorage.getItem('mainCategoriesSelected'));
    //   console.log(this.productCategoryFilter.mainCategoriesSelected);
    // }
    //
    // if (sessionStorage.getItem('subCategoriesFirstSelected')) {
    //   this.productCategoryFilter.subCategoriesFirstSelected =
    //     JSON.parse(sessionStorage.getItem('subCategoriesFirstSelected'));
    //   console.log(this.productCategoryFilter.subCategoriesFirstSelected);
    // }
    //
    // if (sessionStorage.getItem('subCategoriesSecondSelected')) {
    //   this.productCategoryFilter.subCategoriesSecondSelected =
    //     JSON.parse(sessionStorage.getItem('subCategoriesSecondSelected'));
    //   console.log(this.productCategoryFilter.subCategoriesSecondSelected);
    // }
    //
    // console.log("ProductCatgeoryFilter");
    // console.log(this.productCategoryFilter);

    // this.renderCategoryLists();

    // if (!sessionStorage.getItem('mainCategoriesSelected') ||
    //   !sessionStorage.getItem('subCategoriesFirstSelected') ||
    //   !sessionStorage.getItem('subCategoriesSecondSelected')) {
    //   if (this.isPaged) {
    //     this.currentPage = JSON.parse(sessionStorage.getItem('currentPage'));
    //   } else {
    //     this.currentPage = 1;
    //   }
      this.getMovies();
    // } else {
    //   if (this.isFirstFiltered) {
    //     this.currentPage = 1;
    //   } else {
    //     this.currentPage = JSON.parse(sessionStorage.getItem('currentPage'));
    //   }
    //   this.getProductsFilteredByCategories(this.productCategoryFilter);
    // }
  }

  details(id: number) {
    this.router.navigate(['/movie-details/', id]);
  }

  // edit(id: number) {
  //   this.router.navigate(['/edit-product/', id]);
  // }

  // addToCart(selectedId: number): void {
  //   for (let productListItem of this.productListItems) {
  //     if (productListItem.id === selectedId) {
  //       this.productListItemSelected = productListItem;
  //     }
  //   }
  //
  //   this.productCartItemToAdd = {
  //     productId: this.productListItemSelected.id,
  //     name: this.productListItemSelected.name,
  //     itemNumber: this.productListItemSelected.itemNumber,
  //     pictureUrl: this.productListItemSelected.pictureUrl,
  //     quantity: 1 //quantity 1 by default
  //   }
  //
  //   console.log(this.productCartItemToAdd);
  //
  //   if (!localStorage.getItem('cart')) {
  //     this.productCartItems.unshift(this.productCartItemToAdd);
  //     this.alertMessageIfProductAdded();
  //   } else {
  //     this.productCartItems = JSON.parse(localStorage.getItem('cart'));
  //     if (!this.productCartItems.some(item => item.productId === this.productListItemSelected.id)) {
  //       this.productCartItems.unshift(this.productCartItemToAdd);
  //       this.alertMessageIfProductAdded();
  //     }
  //   }
  //
  //   localStorage.setItem('cart', JSON.stringify(this.productCartItems));
  //   console.log('Cart refreshed in localStorage');
  // }

  // private alertMessageIfProductAdded() {
  //   this.cartAlert("&nbsp;" + this.productCartItemToAdd.name + " is added to Cart&nbsp;", 4000);
  // };

  // private cartAlert(message: string, duration: number): void {
  //   document.getElementById("Cart-message").style.opacity = "1";
  //
  //   let element = document.createElement("div");
  //   element.innerHTML = message;
  //   setTimeout(function (): void {
  //     element.parentNode?.removeChild(element);
  //
  //     if (document.getElementById("Cart-message")?.children.length === 0) {
  //       document.getElementById("Cart-message").style.opacity = "0";
  //     }
  //   }, duration);
  //   document.getElementById("Cart-message").appendChild(element);
  // }

  // deleteProduct(id: number) {
  //   this.productService.deleteProduct(id).subscribe({
  //     next: () => {
  //       this.ngOnInit();
  //     },
  //     error: error => {
  //       console.log(error);
  //       this.ngOnInit();
  //     }
  //   });
  // }


  // private getProducts() {
  //   this.productService.getAllProducts(this.currentPage - 1, this.paginationLimit).subscribe({
  //     next: (data: PaginationDataModel) => {
  //       this.productListItems = data.productListItemList
  //       this.paginationTotal = data.total
  //       console.log("total products: " + this.paginationTotal);
  //     },
  //     error: (error) => console.warn(error),
  //     complete: () => {
  //       sessionStorage.setItem('currentPage', JSON.stringify(this.currentPage));
  //     }
  //   });
  // }

  private getMovies() {
    this.movieService.getAllMovies(this.currentPage).subscribe({
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

  // private renderCategoryLists() {
  //   this.productCategoryService.getAllProductMainCategory().subscribe({
  //     next: (data: ProductCategoryListItemModel[]) => {
  //       this.mainCategories = [...data].reverse();
  //       this.createCheckboxControlsForMainCategories();
  //     },
  //     error: (error) => console.warn(error),
  //     complete: () => {
  //       console.log("Main-Category list rendering is ready!");
  //       console.log(this.mainCategories);
  //     }
  //   });
  //   this.productCategoryService.getAllProductSubCategoryFirst().subscribe({
  //     next: (data: ProductCategoryListItemModel[]) => {
  //       this.subCategoriesFirst = [...data].reverse();
  //       this.createCheckboxControlsForSubCategoriesFirst();
  //     },
  //     error: (error) => console.warn(error),
  //     complete: () => {
  //       console.log("Sub-Category-First list rendering is ready!");
  //       console.log(this.subCategoriesFirst);
  //     }
  //   });
  //   this.productCategoryService.getAllProductSubCategorySecond().subscribe({
  //     next: (data: ProductCategoryListItemModel[]) => {
  //       this.subCategoriesSecond = [...data].reverse();
  //       this.createCheckboxControlsForSubCategoriesSecond();
  //     },
  //     error: (error) => console.warn(error),
  //     complete: () => {
  //       console.log("Sub-Category-Second list rendering is ready!");
  //       console.log(this.subCategoriesSecond);
  //     }
  //   });
  // }

  // private createCheckboxControlsForMainCategories(): void {
  //   this.mainCategories.forEach(() => {
  //     const control = new FormControl(false);
  //     (<FormArray>this.productCategoryForm.controls['mainCategoriesSelected']).push(control);
  //   });
  // }

  // private createCheckboxControlsForSubCategoriesFirst(): void {
  //   this.subCategoriesFirst.forEach(() => {
  //     const control = new FormControl(false);
  //     (<FormArray>this.productCategoryForm.controls['subCategoriesFirstSelected']).push(control);
  //   });
  // }

  // private createCheckboxControlsForSubCategoriesSecond(): void {
  //   this.subCategoriesSecond.forEach(() => {
  //     const control = new FormControl(false);
  //     (<FormArray>this.productCategoryForm.controls['subCategoriesSecondSelected']).push(control);
  //   });
  // }

  // onSubmitFilterByCategories() {
  //   const data: ProductCategoryFilterModel = this.productCategoryForm.value;
  //
  //   data.mainCategoriesSelected = this.createMainCategoriesArrayToSend();
  //   if (data.mainCategoriesSelected.length === 0) {
  //     data.mainCategoriesSelected = this.mainCategories
  //       .map(maincategory => maincategory.category);
  //   }
  //   this.productCategoryFilter.mainCategoriesSelected = data.mainCategoriesSelected;
  //
  //   data.subCategoriesFirstSelected = this.createSubCategoriesFirstArrayToSend();
  //   if (data.subCategoriesFirstSelected.length === 0) {
  //     data.subCategoriesFirstSelected = this.subCategoriesFirst
  //       .map(subCategoryFirst => subCategoryFirst.category);
  //   }
  //   this.productCategoryFilter.subCategoriesFirstSelected = data.subCategoriesFirstSelected;
  //
  //   data.subCategoriesSecondSelected = this.createSubCategoriesSecondArrayToSend();
  //   if (data.subCategoriesSecondSelected.length === 0) {
  //     data.subCategoriesSecondSelected = this.subCategoriesSecond
  //       .map(subCategorySecond => subCategorySecond.category);
  //   }
  //   this.productCategoryFilter.subCategoriesSecondSelected = data.subCategoriesSecondSelected;
  //
  //   console.log(data);
  //   sessionStorage.setItem('isFiltered', JSON.stringify(true));
  //   if (JSON.parse(sessionStorage.getItem('currentPage')) > 1) {
  //     sessionStorage.setItem('isFirstFiltered', JSON.stringify(true));
  //     this.currentPage = 1;
  //   }
  //
  //   this.getProductsFilteredByCategories(data);
  //
  //   if (document.documentElement.clientWidth < 768) {this.hideFilters()}
  //
  // }

  // private createMainCategoriesArrayToSend(): string[] {
  //   return this.productCategoryForm.value.mainCategoriesSelected
  //     .map((mainCategory: string, index: number) => mainCategory ? this.mainCategories[index].category : null)
  //     .filter((mainCategory: string) => mainCategory !== null);
  // }

  // private createSubCategoriesFirstArrayToSend(): string[] {
  //   return this.productCategoryForm.value.subCategoriesFirstSelected
  //     .map((subCategoryFirst: string, index: number) => subCategoryFirst ? this.subCategoriesFirst[index].category : null)
  //     .filter((subCategoryFirst: string) => subCategoryFirst !== null);
  // }

  // private createSubCategoriesSecondArrayToSend(): string[] {
  //   return this.productCategoryForm.value.subCategoriesSecondSelected
  //     .map((subCategorySecond: string, index: number) => subCategorySecond ? this.subCategoriesSecond[index].category : null)
  //     .filter((subCategorySecond: string) => subCategorySecond !== null);
  // }

  // private getProductsFilteredByCategories(data: ProductCategoryFilterModel) {
  //   this.productService.getProductListFilteredPaginated(this.currentPage - 1, this.paginationLimit, data).subscribe({
  //     next: (data: PaginationDataModel) => {
  //       this.productListItems = data.productListItemList;
  //       this.paginationTotal = data.total;
  //     },
  //     error: (error) => console.warn(error),
  //     complete: () => {
  //       sessionStorage.setItem('mainCategoriesSelected', JSON.stringify(data.mainCategoriesSelected));
  //       sessionStorage.setItem('subCategoriesFirstSelected', JSON.stringify(data.subCategoriesFirstSelected));
  //       sessionStorage.setItem('subCategoriesSecondSelected', JSON.stringify(data.subCategoriesSecondSelected));
  //       sessionStorage.setItem('currentPage', JSON.stringify(this.currentPage));
  //
  //       this.productCategoryForm.patchValue({
  //         mainCategoriesSelected:
  //           this.createMainCategoriesSelectedFormArray(this.productCategoryFilter.mainCategoriesSelected),
  //         subCategoriesFirstSelected:
  //           this.createSubCategoriesFirstSelectedFormArray(this.productCategoryFilter.subCategoriesFirstSelected),
  //         subCategoriesSecondSelected:
  //           this.createSubCategoriesSecondSelectedFormArray(this.productCategoryFilter.subCategoriesSecondSelected),
  //       });
  //     }
  //   });
  // }

  // createMainCategoriesSelectedFormArray = (mainCategoriesSelected: string[]) => {
  //   if (this.productCategoryFilter.mainCategoriesSelected.length ===
  //     this.mainCategories.length) {
  //     return [];
  //   }
  //
  //   return this.mainCategories.map(mainCategory =>
  //     mainCategoriesSelected.includes(mainCategory.category));
  // };

  // createSubCategoriesFirstSelectedFormArray = (subCategoriesFirstSelected: string[]) => {
  //   if (this.productCategoryFilter.subCategoriesFirstSelected.length ===
  //     this.subCategoriesFirst.length) {
  //     return [];
  //   }
  //
  //   return this.subCategoriesFirst.map(subCategoryFirst =>
  //     subCategoriesFirstSelected.includes(subCategoryFirst.category));
  // };

  // createSubCategoriesSecondSelectedFormArray = (subCategoriesSecondSelected: string[]) => {
  //   if (this.productCategoryFilter.subCategoriesSecondSelected.length ===
  //     this.subCategoriesSecond.length) {
  //     return [];
  //   }
  //
  //   return this.subCategoriesSecond.map(subCategorySecond =>
  //     subCategoriesSecondSelected.includes(subCategorySecond.category));
  // };


  // clearAllFilter() {
  //   sessionStorage.removeItem('mainCategoriesSelected');
  //   sessionStorage.removeItem('subCategoriesFirstSelected');
  //   sessionStorage.removeItem('subCategoriesSecondSelected');
  //   sessionStorage.removeItem('currentPage');
  //   sessionStorage.removeItem('isFirstFiltered');
  //   sessionStorage.removeItem('isFiltered');
  //
  //   this.isFiltered = false;
  //   this.isFirstFiltered = false;
  //   this.isPaged = false;
  //
  //   this.productCategoryFilter.mainCategoriesSelected = [];
  //   this.productCategoryFilter.subCategoriesFirstSelected = [];
  //   this.productCategoryFilter.subCategoriesSecondSelected = [];
  //
  //   console.log(this.productCategoryFilter);
  //
  //   this.ngOnInit();
  //
  //   if (document.documentElement.clientWidth < 768) {this.hideFilters()}
  // }

  changePage(page: number): void {
    console.log(page);
    this.currentPage = page;
    this.getMovies();

    // if (sessionStorage.getItem('isFiltered')) {
    //   sessionStorage.removeItem('isFirstFiltered');
    //   this.getProductsFilteredByCategories(this.productCategoryFilter);
    // } else {
    //   this.getProducts();
    // }
  }


  // showFilters() {
  //   this.filtersVisible = true;
  //   this.toggleFilterLayout();
  //   this.userChangedFilterVisibility = true;
  //
  //   const rightDiv = document.getElementById("rightDiv");
  //   const screenWidth = document.documentElement.clientWidth;
  //
  //   if (screenWidth < 768) {
  //     rightDiv.style.display = "none";
  //   } else {
  //     rightDiv.style.display = "";
  //   }
  // }


  // hideFilters() {
  //   this.filtersVisible = false;
  //   this.toggleFilterLayout();
  //   this.userChangedFilterVisibility = true;
  //
  //   const rightDiv = document.getElementById("rightDiv");
  //   rightDiv.style.display = "";
  // }

  // toggleFilters() {
  //   this.filtersVisible = !this.filtersVisible;
  //   this.toggleFilterLayout();
  //   this.userChangedFilterVisibility = true;
  //
  //   const rightDiv = document.getElementById("rightDiv");
  //   if (this.smallMobileScreen()) {
  //     rightDiv.style.display = "none";
  //
  //   } else {
  //     rightDiv.style.display = "";
  //   }
  //
  // }

  // toggleFilterLayout() {
  //   if(document.getElementById("rightDiv") != null)
  //   {
  //     document.getElementById("rightDiv").style.marginLeft
  //       = this.filtersVisible ? "250px" : "0";
  //   }
  // }

  // smallMobileScreen(): any {
  //   const screenSizeQuery : string
  //     = "(max-width : 768px)";
  //   const smallSizedScreen =
  //     window.matchMedia(screenSizeQuery);
  //   return smallSizedScreen.matches;
  // }

  // initMobileLayout() {
  //   const screenSizeQuery : string
  //     = "(min-width : 768px)";
  //   const largerSizedScreen =
  //     window.matchMedia(screenSizeQuery);
  //
  //   this.filtersVisible = largerSizedScreen.matches;
  // }



}
