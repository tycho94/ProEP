import { Component } from '@angular/core';
import { HTTP_PROVIDERS } from '@angular/http';
import { ROUTER_DIRECTIVES } from '@angular/router';
import './rxjs-operators';

@Component({
    selector: 'app',
    template: `
        <router-outlet></router-outlet>
    `,
    directives: [
        ROUTER_DIRECTIVES
    ],
    providers: [
        HTTP_PROVIDERS
    ]
})

export class AppComponent {

}
