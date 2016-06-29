import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Account } from './account.model';
import { Observable }     from 'rxjs/Observable';


@Injectable()
export class AccountService {
    private baseUrl = 'http://localhost:1337/?csurl=http://192.168.20.24:8080/proeprest/api/user'
    private user: Account;

    constructor (private http: Http) {}

    private post (path: string, data: Account): Observable<Account> {
        let url = `${this.baseUrl}/${path}`;
        let body = JSON.stringify(data);
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });

        console.log(url);
        console.log(body);
        return this.http
            .post(url, body, options)
            .map(this.extractData)
            .catch(this.handleError);
    }

    private get (path: string): Observable<Account> {
        let url = `${this.baseUrl}/${path}`;

        return this.http
            .get(url)
            .map(this.extractData)
            .catch(this.handleError);
    }

    createAccount (account: Account) {
        let path = `singup`;

        return this.post(path, account);
    }

    login (account: Account) {
        let path = `login/${account.username}`;

        return this.post(path, account.password);
    }

    loggedIn () {
       return this.user ? true : false;
    }

    getCurrentUser () {
        return this.user;
    }

    getUser(name: string) {
        let path = `name/${this.user.username}`;

        return this.get(path);
    }

    private handleError (error: any) {
        let errMsg = (error.message) ? error.message :
            error.status ? `${error.status} - ${error.statusText}` : 'Server error';
        console.error(errMsg); // log to console instead
        return Observable.throw(errMsg);
    }

    private extractData (res: Response) {
        let body = res.json();
        return body.data || { };
    }
}
