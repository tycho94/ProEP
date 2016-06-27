import { Component } from '@angular/core';
import { NavigationMenuComponent } from './navigation.menu.component';

@Component({
    template: `
        <navigation-menu></navigation-menu>
    `,
    directives: [
        NavigationMenuComponent,
    ]
})

export class HomeComponent { }
