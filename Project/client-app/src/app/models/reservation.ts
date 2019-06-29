import {MessageTable} from './messageTable';

export class Reservation {
  checkIn:Date;
  checkOut:Date;
  state:string;
  messageTable:MessageTable[]
}
