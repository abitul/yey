package apifutsal


import grails.rest.*
import grails.converters.*

class StadionController {

    static responseFormats = ['json']

    StadionService stadionService
    def responseOfRequest

    def show(params) { 
        print params
        responseOfRequest = stadionService.showData(params)
        respond responseOfRequest
    }

    def save(){
        print request.JSON
        responseOfRequest = stadionService.saveData(request.JSON)
        respond responseOfRequest
    }

    def update(){
        print request.JSON
        responseOfRequest = stadionService.updateData(request.JSON)
        respond responseOfRequest
    }

    def delete(){
        print request.JSON
        responseOfRequest = stadionService.deleteData(request.JSON)
        respond responseOfRequest
    }
}
