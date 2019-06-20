import { MessageTable } from './message-table';

export class SendMessageDto {
    roomId:Number;
    reservationId:Number;
    messageTable:MessageTable;

    constructor(roomId,resId,message){
        this.roomId = roomId;
        this.reservationId = resId;
        this.messageTable = message;
    }
}
