import{Location} from "./location"
import { PriceList } from "./price-list";
import { RoomAdditionalServices } from './room-additional-services';
import { RoomCategory } from './room-category';
import { RoomType } from './room-type';
export class Room {

    id:number;
    numberOfBeds:number;
    location:Location;
    accommodationType:RoomType;
    accommodationCategory:RoomCategory ;
    roomAdditionalService:Array<RoomAdditionalServices>;
    description : string;
    image:Array<string>;
    priceList:Array<PriceList>;
    reservation:Array<any>;
    daysForCancel:number;

    constructor(){
        
    }
}


