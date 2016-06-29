import { Component } from '@angular/core';
import { PopupDirective } from './popup.directive';
import { ROUTER_DIRECTIVES } from '@angular/router';

@Component({
    selector: 'navigation-menu',
    templateUrl: '/partials/navigation.menu.component.html',
    directives: [
        ROUTER_DIRECTIVES,
        PopupDirective,
    ],
})

export class NavigationMenuComponent { }
