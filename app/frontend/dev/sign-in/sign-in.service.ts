/**
 * Created by Adjoa on 5/27/2016.
 */
import {Injectable} from '@angular/core';
import {Http, Headers} from '@angular/http';


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
            .map(response => response.json());
    }
}