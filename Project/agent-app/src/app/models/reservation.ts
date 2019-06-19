import { MessageTable } from './message-table';

export class Reservation {
    checkIn:Date;
    checkOut:Date;
    id:Number
    state:ReservationStateEnum;
    messageTable:Array<MessageTable>

    constructor(  checkIn:Date,
        checkOut:Date,
        state:ReservationStateEnum,
   ){

            this.checkIn=checkIn;
            this.checkOut=checkOut;
            this.state = state;
    }
}

export enum ReservationStateEnum{
    ALLOWED,
    NOTALLOWED,
    PENDING
}

