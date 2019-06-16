import { PrivilegeEnum } from './privilege-enum';

export class Role {
    id: number;
    name: string;
    privileges: Array<PrivilegeEnum>;

    constructor() {
        this.privileges = Array<PrivilegeEnum>();
    }
}
