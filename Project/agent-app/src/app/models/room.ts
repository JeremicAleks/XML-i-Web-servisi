import{Location} from "./location"
import { PriceList } from "./price-list";
export class Room {

    id:number;
    numberOfBeds:number;
    location:Location;
    type:TypeEnum;
    category:CategoryEnum ;
    additionalServices:Array<RoomAdditionalServiceEnum>;
    description : string;
    image:Array<Array<any>>;
    priceList:Array<PriceList>;
    reservation:Array<any>;

    constructor(){
        
    }
}

export enum TypeEnum{

    HOTEL,
    BEDANDBREKFAST,
    APARTMAN

}

export enum RoomAdditionalServiceEnum {

    WIFI,
    PARKING,
    BREAKFAST,
    BEDANDBREKFAST,
    BEDANDBOARD,
    ALLINCLUSIVE,
    PETFRIENDLY,
    TV,
    KITCHEN,
    BATHROOM,
    ROOMCANCEL

}


export enum CategoryEnum {


    UNCATEGORIZAED,
    ONESTAR,
    TWOSTAR,
    THREESTAR,
    FOURSTAR,
    FIVESTAR

}
