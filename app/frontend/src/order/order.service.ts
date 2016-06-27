import { Injectable } from '@angular/core';


@Injectable()
export class OrderService {
    getOrders() {
        console.log('get multiple orders');
    }

    getOrder() {
        console.log('get single order');
    }
}
