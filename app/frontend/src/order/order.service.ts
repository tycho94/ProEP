import { Injectable } from '@angular/core';
import { Order } from './order.model';
import { Http, Response } from '@angular/http';
import { Observable }     from 'rxjs/Observable';


@Injectable()
export class OrderService {
    private baseUrl = 'http://localhost:1337/?csurl=http://192.168.20.24:8080/proeprest/api/'

    constructor (private http: Http) {}

    getOrders (): Observable<Order[]> {
        return this.http
            .get(this.baseUrl + 'order/all')
            .map(this.extractData)
            .catch(this.handleError);
    }

    getOrder (id: number): Observable<Order> {
        return this.http
            .get(this.baseUrl + 'order/' + id)
            .map(this.extractData)
            .catch(this.handleError);
    }

    private extractData (res: Response) {
        return res.json() || { };
    }

    private handleError (error: any) {
        let errMsg = (error.message) ? error.message :
            error.status ? `${error.status} - ${error.statusText}` : 'Server error';
        console.error(errMsg); // log to console instead
        return Observable.throw(errMsg);
    }
}
