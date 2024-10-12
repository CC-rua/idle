import http from "../http";

export default {
    sayhello(){
        return http.get('/admin-api/game-user/test/hello');
    }
}