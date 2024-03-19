import { Component } from '@angular/core';

@Component({
  selector: 'app-nav-simple',
  templateUrl: './nav-simple.component.html',
  styleUrls: ['./nav-simple.component.css']
})
export class NavSimpleComponent {

  hideMenu(){
    if (document.getElementById("nav-check") != null) {
      (document.getElementById("nav-check") as HTMLInputElement)
        .checked = false;
    }
  }

}
