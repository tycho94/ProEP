import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Account } from './account.model';
import { Observable }     from 'rxjs/Observable';


@Injectable()
export class AccountService {
    private accountUrl = 'http://192.168.20.24:8080/proeprest/account/create';

    constructor (private http: Http) {}

    post (path: string, data: Account): Observable<Account> {
        let body = JSON.stringify(data);
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });

        return this.http
            .post(this.accountUrl + path, body, options)
            .map(this.extractData)
            .catch(this.handleError);
    }

    delete (account: Account) {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let url = `${this.accountUrl}/${account.id}`;

        return this.http
            .delete(url, headers)
            .map(this.extractData)
            .catch(this.handleError);
    }

    public login (account: Account) {
        return this.post('/login', account)
    }

    public createAccount (account: Account) {
        return this.post('/create', account)
    }

    handleError (error: any) {
        let errMsg = (error.message) ? error.message :
            error.status ? `${error.status} - ${error.statusText}` : 'Server error';
        console.error(errMsg); // log to console instead
        return Observable.throw(errMsg);
    }

    extractData (res: Response) {
        let body = res.json();
        return body.data || { };
    }
}
