import { Component } from '@angular/core';
import { AccountService } from "./account.service";


@Component({
    selector: 'account-create',
    providers: [AccountService],
    template: '/partials/account.create.component.html'
})

export class AccountCreateComponent {
    constructor (private service: AccountService) {}
}
