import { UserStatusEnum } from './user-status-enum';

export class UserLoginDTO {
    username: string;
    name: string;
    lastName: string;
    email: string;
    adress: string;
    telephone: string;
    token: string;
    role: string;
    userStatus: UserStatusEnum;

    constructor() { }


}
