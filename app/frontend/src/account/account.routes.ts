import { RouterConfig }          from '@angular/router';
import { AccountLoginComponent } from './account-login.component';
import { AccountCreateComponent } from './account-create.component';

export const AccountRoutes: RouterConfig = [
    { path: 'account/login',  component: AccountLoginComponent },
    { path: 'account/create',  component: AccountCreateComponent },
];
