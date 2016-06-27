import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import 'rxjs/add/operator/toPromise';


@Injectable()
export class SignInService
{
    http: Http;

    constructor (http: Http) {
        this.http = http;
    }

    postData(data: any) {
        const body = JSON.stringify(data);
        const headers = new Headers();
        headers.append('Content-Type', 'application/json');
        return this.http
            .post('https://demo6816634.mockable.io/data.json', body, {headers: headers})
            .toPromise();
    }
}
