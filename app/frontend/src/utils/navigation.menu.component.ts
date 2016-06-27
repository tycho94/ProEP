import { Component } from '@angular/core';
import { PopupDirective } from './popup.directive';

@Component({
    selector: 'navigation-menu',
    templateUrl: '/static/partials/navigation.menu.component.html',
    directives: [PopupDirective],
})

export class NavigationMenuComponent { }
