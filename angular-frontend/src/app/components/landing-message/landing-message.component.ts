import {Component, OnInit} from '@angular/core';

@Component({
    selector: 'app-landing-message',
    templateUrl: './landing-message.component.html',
    styleUrls: ['./landing-message.component.css']
})
export class LandingMessageComponent implements OnInit {

    showLandingMessage!: boolean;

    ngOnInit(): void {
        this.loadLandingMessageState();
    }


    hideLandingMessage(): void {
        this.showLandingMessage = false;
        this.saveLandingMessageState();
    }

    loadLandingMessageState(): void {
        if (sessionStorage.getItem("hideLandingMessage")) {
            this.showLandingMessage = false;
        } else {
            this.showLandingMessage = true;
        }
    }

    saveLandingMessageState(): void {
        if (!this.showLandingMessage) {
            sessionStorage.setItem("hideLandingMessage", "true");
        } else {
            sessionStorage.removeItem("hideLandingMessage");
        }
    }


}
