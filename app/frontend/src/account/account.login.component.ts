import { AccountService } from "./account.service";
import { Component, OnInit } from '@angular/core';
import { Account } from './account.model';
import { Address } from './address.model';
import { NavigationMenuComponent } from '../utils/navigation.menu.component';
import { NgForm } from '@angular/common';
import { Router } from '@angular/router';

@Component({
    selector: 'account-login',
    templateUrl: '/partials/account.login.component.html',
    directives: [NavigationMenuComponent],
    providers: [AccountService]
})

export class AccountLoginComponent implements OnInit {
    error: any;
    account: Account;

    constructor (
        private router: Router,
        private service: AccountService
    ) {}

    ngOnInit() {
        this.account = new Account(new Address("", "", "", ""), "", "", "");
    }

    onSubmit() {
        this.service.login(this.account).subscribe(
            smt => console.log(smt),
            err => console.error(err)
        );
        this.toOrders();
    }

	toCreateAccount() {
        this.router.navigate(['/account/create']);
	}

	toOrders() {
        this.router.navigate(['/order/list']);
	}

    get diagnostic() {
        return JSON.stringify(this.account);
    }
}
