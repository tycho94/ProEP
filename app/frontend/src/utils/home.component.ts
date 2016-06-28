import { Component } from '@angular/core';
import { NavigationMenuComponent } from './navigation.menu.component';

@Component({
    selector: 'home',
    templateUrl: '/partials/home.component.html',
    directives: [
        NavigationMenuComponent,
    ]
})

export class HomeComponent { }
