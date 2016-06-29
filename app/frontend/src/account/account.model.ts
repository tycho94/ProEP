import { Address } from './address.model';

export class Account {
  constructor(
    public address: Address,
    public username: string,
    public password: string,
    public email: string
  ) {  }
}
