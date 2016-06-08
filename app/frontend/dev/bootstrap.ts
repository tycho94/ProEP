import { bootstrap } from '@angular/platform-browser-dynamic';
import { HTTP_PROVIDERS } from "@angular/http";
import { SignInComponent } from "./sign-in/sign-in.component";


bootstrap(SignInComponent, [HTTP_PROVIDERS]);
