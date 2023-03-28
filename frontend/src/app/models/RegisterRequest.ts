export class RegisterRequest {
    constructor(
        public username: string,
        public email: string,
        public password: string,
        public phone: string,
        public speciality: string,
        public picture: string

    ){}
}