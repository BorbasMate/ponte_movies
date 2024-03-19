import { Component } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-nav-error',
  templateUrl: './nav-error.component.html',
  styleUrls: ['./nav-error.component.css']
})
export class NavErrorComponent {

  constructor(private router: Router) {
  }

  goHome(): void {
    this.router.navigate(['/']);
  }

}
