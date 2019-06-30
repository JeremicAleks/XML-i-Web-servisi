export class SearchParams {
 public destination: string;
 public checkIn: Date;
 public checkOut: Date;
 public numOfPeople: number;
 

  constructor(destination, checkIn, checkOut, numOfPeople) {
   this.destination = destination;
   this.checkIn = checkIn;
   this.checkOut = checkOut;
   this.numOfPeople = numOfPeople;
  }

}
