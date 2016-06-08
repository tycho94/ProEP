/**
 * Created by Adjoa on 5/27/2016.
 */

import { Component } from '@angular/core';
import { SignInService } from "./sign-in.service";


@Component({
    selector: 'sign-in',
    template: `
        <h1>Hello, world!</h1>
    `,
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
        this.signInService
        .postData(userDatadata)
        .subscribe(
            data => this.response = JSON.stringify(data),
            error => console.log(error)
        );
    };
}
