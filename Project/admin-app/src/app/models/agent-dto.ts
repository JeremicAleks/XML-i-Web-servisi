export class AgentDTO {
    name: string;
    lastName: string;
    address: string;
    businessRegNumber: string;

    constructor(name: string, lastName: string, address: string, businessRegNumber: string) {
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.businessRegNumber = businessRegNumber;
     }
}
