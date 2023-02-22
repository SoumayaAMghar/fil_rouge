export class LoginResponse {
    constructor(
        public accessToken: string,
        public email: string,
        public password: string
    ){}
}