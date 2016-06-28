import { Injectable } from '@angular/core';
import { Order } from './order.model';
import { Http, Response, Headers } from '@angular/http';
import { Observable }     from 'rxjs/Observable';


@Injectable()
export class OrderService {
    private orderUrl = 'http://192.168.20.24:8080/proeprest/api/orders';

    constructor (private http: Http) {}

    public getOrders (): Observable<Order[]> {
        return this.http
            .get('http://192.168.20.24:8080/proeprest/api/item/1')
            .map(this.extractData)
            .catch(this.handleError);
    }

    public getOrder (id: number): Observable<Order> {
        return this.http
            .get('http://192.168.20.24:8080/proeprest/api/item/1')
            .map(this.extractData)
            .catch(this.handleError);
    }

    extractData (res: Response) {
        let body = res.json();
        return body.data || { };
    }

    handleError (error: any) {
        let errMsg = (error.message) ? error.message :
            error.status ? `${error.status} - ${error.statusText}` : 'Server error';
        console.error(errMsg); // log to console instead
        return Observable.throw(errMsg);
    }
}
