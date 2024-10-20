import http from "../http";

export default {
    idleStart(param) {
        return http.get('/app-api/game-user/idle/start', param);
    },
    idleEnd(param) {
        return http.get('/app-api/game-user/idle/end', param);
    }
}