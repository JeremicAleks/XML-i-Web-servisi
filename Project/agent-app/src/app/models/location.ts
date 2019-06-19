export class Location {

    id:number;
    lat:number;
    lng:number;
    name:String;

    constructor(lat,lng,name){
        this.lat=lat;
        this.lng =lng;
        this.name = name;
    }
}
