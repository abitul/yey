package apifutsal


import grails.rest.*
import grails.converters.*

class futsalFieldController {

    static responseFormats = ['json']     
    static allowedMethods = [save: "POST", show: "GET", update: "PUT", delete: "DELETE"]

    FutsalFieldService futsalFieldService
    def responseOfRequest

    def show(params) { 
        print params
        responseOfRequest = futsalFieldService.showData(params)
        respond responseOfRequest
    }

    def save(){
        print request.JSON
        responseOfRequest = futsalFieldService.saveData(request.JSON)
        respond responseOfRequest
    }

    def update(){
        print request.JSON
        responseOfRequest = futsalFieldService.updateData(request.JSON)
        respond responseOfRequest
    }

    def delete(){
        print request.JSON
        responseOfRequest = futsalFieldService.deleteData(request.JSON)
        respond responseOfRequest
    }
}
