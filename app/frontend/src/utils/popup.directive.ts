import { ViewChild, Directive, ElementRef, Input } from '@angular/core';
declare var $: any;

@Directive({
    selector: '[popup]',
    host: {
        '(click)': 'onClicked($event)',
    },
})

export class PopupDirective {
    onClicked($event) {
        $(".menu_body").fadeToggle(500);
    }
}
