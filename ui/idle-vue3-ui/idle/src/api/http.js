import axios from 'axios';
function myAxios(config) {
    return axios(config)
}
export default {

    get(url, param) {
        return myAxios({
            url: url,
            method: "get",
            data: param,
        });
    }
    , post(url, body) {
        return myAxios({
            url: url,
            method: "post",
            data: JSON.stringify(body),
            timeout: 10000,
            responseType: 'json'
        })
    }
}
