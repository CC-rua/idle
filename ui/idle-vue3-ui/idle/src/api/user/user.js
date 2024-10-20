import http from "../http";

export default {
    registerRole(param) {
        return http.post('/app-api/game-user/user/register', param);
    },
    login(param) {
        return http.post('/app-api/game-user/user/login', param);
    }
}