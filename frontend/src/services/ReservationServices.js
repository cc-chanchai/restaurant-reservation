import http from '../http-common'

class ReservationServices {
    getAll(){
        return http.get("/reservations")
    }
    getCurrentQueue(){
        return http.get("/current")
    }

    createQueue(username){
        return http.post("/reservations?name=" + username)
    }
    nextQueueService(currentQueue){
        return http.put("/reservations/" + currentQueue)
    }
}

export default new ReservationServices();