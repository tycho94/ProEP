import { Injectable } from '@angular/core';
import { Order } from './order.model';
import { Http, Response } from '@angular/http';
import { Observable }     from 'rxjs/Observable';


@Injectable()
export class OrderService {
    private baseUrl = 'http://localhost:1337/?url=http://192.168.20.24:8080/proeprest/api/order/'

    constructor (private http: Http) {}

    getOrders (): Observable<Order[]> {
        return this.http
            .get(this.baseUrl + 'all')
            .map(this.extractData)
            .catch(this.handleError);
    }

    getOrder (id: number): Observable<any> {
        return this.http
            .get(this.baseUrl + id)
            .map(this.extractData)
            .catch(this.handleError);
    }

    private extractData (res: Response) {
        if (!res.ok) {
            throw new Error("Bad response. - " + res);
        }
        console.log(res);
        return res.json().contents || { };
    }

    private handleError (error: any) {
        let errMsg = (error.message) ? error.message :
            error.status ? `${error.status} - ${error.statusText}` : 'Server error';
        console.error(errMsg); // log to console instead
        return Observable.throw(errMsg);
    }
}
