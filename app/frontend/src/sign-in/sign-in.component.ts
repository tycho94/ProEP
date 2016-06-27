import { Component } from '@angular/core';
import { SignInService } from "./sign-in.service";


@Component({
    selector: 'sign-in',
    providers: [SignInService],
})

export class SignInComponent {
    service: SignInService;
    response: string;
    userData: {};

    constructor (service: SignInService) {
        this.service = service;
    }

    onPost() {
        this.service
        .postData(this.userData);
    };
}
