import { AccountService } from "./account.service";
import { Component, Input } from '@angular/core';
import { Account } from './account.model';
import { NavigationMenuComponent } from '../utils/navigation.menu.component';
import { NgForm } from '@angular/common';

@Component({
    selector: 'account-login',
    templateUrl: '/partials/account.login.component.html',
    directives: [NavigationMenuComponent],
    providers: [AccountService]
})

export class AccountLoginComponent {
    error: any;
    account: Account;

    constructor (private service: AccountService) {
        this.account = new Account(0, "", "", "");
    }

    onSubmit() {
        this.service.login(this.account).subscribe(
            account => this.account = account,
            error => this.error = <any>error
        );
    }

    get diagnostic() {
        return JSON.stringify(this.account);
    }
}
