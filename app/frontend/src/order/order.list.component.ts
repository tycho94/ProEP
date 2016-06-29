import { Component, OnInit } from '@angular/core';
import { Order } from './order.model';
import { OrderService } from './order.service';
import { NavigationMenuComponent } from '../utils/navigation.menu.component';

@Component({
    selector: 'orders-list',
    templateUrl: '/partials/order.list.component.html',
    directives: [NavigationMenuComponent],
    providers: [OrderService]
})

export class OrderListComponent implements OnInit {
    error: any;
    order: Order;

    constructor (private service: OrderService) {}

    ngOnInit() {
        this.updateOrders();
    }

    updateOrders () {
        this.service.getOrder(1).subscribe(
			function (order) {
				this.order = order;
			},
            function (error) {
				this.error = error;
			}
        );
    }
}
