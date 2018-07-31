package apifutsal

class PlayingController {

    static responseFormats = ['json']     
    static allowedMethods = [save: "POST", show: "GET", update: "PUT", delete: "DELETE"]

    PlayingService playingService
    def responseOfRequest

    def show(params) { 
        print params
        responseOfRequest = playingService.showData(params)
        respond responseOfRequest
    }

    def save(){
        print request.JSON
        responseOfRequest = playingService.saveData(request.JSON)
        respond responseOfRequest
    }

    def update(){
        print request.JSON
        responseOfRequest = playingService.updateData(request.JSON)
        respond responseOfRequest
    }

    def delete(){
        print request.JSON
        responseOfRequest = playingService.deleteData(request.JSON)
        respond responseOfRequest
    }
}
