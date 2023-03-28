export class RegisterResponse {
    constructor(
    
        public username: String,
        public email: string,
        public password: string,
        public phone: String,
        public speciality: String,
        public picture: String
    ){}
}