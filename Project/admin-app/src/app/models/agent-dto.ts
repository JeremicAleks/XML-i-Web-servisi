export class AgentDTO {
    userToPromote: string;
    address: string;
    businessRegNumber: string;

    constructor(userToPromote: string, address: string, businessRegNumber: string) {
        this.userToPromote = userToPromote;
        this.address = address;
        this.businessRegNumber = businessRegNumber;
     }
}
