package apifutsal


import grails.rest.*
import grails.converters.*

class PlayerController {

    static responseFormats = ['json']

    PlayerService playerService
    def responseOfRequest

    def show(params) { 
        print params
        responseOfRequest = playerService.showData(params)
        respond responseOfRequest
    }

    def save(){
        print request.JSON
        responseOfRequest = playerService.saveData(request.JSON)
        respond responseOfRequest
    }

    def update(){
        print request.JSON
        responseOfRequest = playerService.updateData(request.JSON)
        respond responseOfRequest
    }

    def delete(){
        print request.JSON
        responseOfRequest = playerService.deleteData(request.JSON)
        respond responseOfRequest
    }
}
