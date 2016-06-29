import { provideRouter, RouterConfig } from '@angular/router';
import { AboutComponent } from './about/about.component';
import { HomeComponent } from './utils/home.component';
import { AccountRoutes } from './account/account.routes';
import { OrderRoutes } from './order/order.routes';

export const routes: RouterConfig = [
    { path: '', component: HomeComponent },
    { path: 'about', component: AboutComponent },
    ...AccountRoutes,
    ...OrderRoutes
];

export const APP_ROUTER_PROVIDERS = [
    provideRouter(routes)
];
