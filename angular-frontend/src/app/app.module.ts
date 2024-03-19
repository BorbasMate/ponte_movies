import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import { NavSimpleComponent } from './components/nav-simple/nav-simple.component';
import { FooterComponent } from './components/footer/footer.component';
import { PaginationComponent } from './components/pagination/pagination.component';
import { MovieListComponent } from './components/movie-list/movie-list.component';
import { LandingMessageComponent } from './components/landing-message/landing-message.component';
import { MovieDetailsComponent } from './components/movie-details/movie-details.component';
import { NavErrorComponent } from './components/nav-error/nav-error.component';

@NgModule({
  declarations: [
    AppComponent,
    NavSimpleComponent,
    FooterComponent,
    PaginationComponent,
    MovieListComponent,
    LandingMessageComponent,
    MovieDetailsComponent,
    NavErrorComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
