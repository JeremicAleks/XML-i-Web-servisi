import {AccomodationCategory} from './accomodationCategory';
import {RoomAdditionalService} from './roomAdditionalService';
import {PriceList} from './priceList';
import {AccomodationType} from './accomodationType';
import {Location} from './location';
import {Reservation} from './reservation';

export class RoomDto {
  numberOfBeds:number;
  location:Location;
  accomodationCategory:AccomodationCategory;
  roomAdditionalService:RoomAdditionalService[];
  description:string;
  image:string[];
  priceList:PriceList[];
  reservation:Reservation[];
  daysForCancel:number;
  accommodationType:AccomodationType;

  constructor() { }
}
