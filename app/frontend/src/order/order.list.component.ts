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
    orders: Order[];
	totalPrice: number;

    constructor (private service: OrderService) {}

    ngOnInit() {
        this.orders = [];
        this.updateOrders();
    }

    updateOrders () {
        this.service.getOrders().subscribe(
            (orders) => {
                for (let order of orders) {
                    if (order.username === "Tom") {
                        this.orders.push.apply(this.orders, order.orderlist);
                        this.updatePrice();
                    }
                }
            },
		    error => this.error = error
        );
    }

    updatePrice () {
		let price = 0;

		for (let order of this.orders) {
			price += order.price;
		}

		this.totalPrice = price;
    }
}
