import { Component } from '@angular/core';
import { NavigationMenuComponent } from '../utils/navigation.menu.component';

@Component({
    selector: 'home',
    templateUrl: '/partials/about.component.html',
    directives: [NavigationMenuComponent]
})

export class AboutComponent { }
