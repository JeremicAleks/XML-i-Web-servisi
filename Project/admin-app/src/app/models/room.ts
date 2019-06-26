export class Room {

    id: number;
    numberOfBeds: number;
    location: Location;
    // accommodationType: RoomType;
    // accommodationCategory: RoomCategory;
    // roomAdditionalService: Array<AdditionalSer>;
    description: string;
    image: Array<string>;
    // priceList: Array<PriceList>;
    reservation: Array<any>;
    daysForCancel: number;

    constructor() {}
}
