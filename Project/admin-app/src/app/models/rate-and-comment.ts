import { RouterModule } from '@angular/router';
import { Room } from './room';

export class RateAndComment {
    comment: string;
    rating: number;
    isAllowed: boolean;
    room: any;
    id: number;
    reservation: any;
    regUser: any;

    constructor() {}
}
