import { Component, OnInit } from '@angular/core';
import { AccountService } from "./account.service";
import { Account } from './account.model';
import { Address } from './address.model';
import { NavigationMenuComponent } from '../utils/navigation.menu.component';
import { NgForm } from '@angular/common';
import { Router } from '@angular/router';

@Component({
    selector: 'account-create',
    templateUrl: '/partials/account.create.component.html',
    directives: [NavigationMenuComponent],
    providers: [AccountService]
})

export class AccountCreateComponent implements OnInit {
    account: Account;

    constructor (
        private router: Router,
        private service: AccountService
    ) {}

    ngOnInit() {
        this.account = new Account(new Address("", "", "", ""), "", "", "");
    }

    onSubmit() {
        this.service.createAccount(this.account).subscribe(
            smt => console.log('smt2'),
            err => console.error(err)
        );
        this.router.navigate(["/account/login"]);
    }
}
