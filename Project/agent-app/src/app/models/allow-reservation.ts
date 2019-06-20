import { ReservationStateEnum } from './reservation';

export class AllowReservation {

    reservationId:Number;
    state:ReservationStateEnum;

    constructor(reservationId,state){
        this.reservationId=reservationId;
        this.state = state;
    }
}

