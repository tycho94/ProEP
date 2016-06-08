import { ViewChild, Directive, ElementRef, Input } from '@angular/core';

@Directive({
    selector: '[popup]',
    host: {
        '(click)': 'onClicked($event)',
    },
})

export class PopupDirective {
    private originElement: HTMLElement;

    constructor (originElement: ElementRef) {
        this.originElement = originElement.nativeElement;
    }

    onClicked($event) {
        console.log(this.originElement);
        console.log($event);
    }
}
