package apifutsal


import grails.rest.*
import grails.converters.*


class TeamController {

    static responseFormats = ['json']     
    static allowedMethods = [save: "POST", show: "GET", update: "PUT", delete: "DELETE"]

    TeamService teamService
    def responseOfRequest

    def show(params) { 
        print params
        responseOfRequest = teamService.showData(params)
        respond responseOfRequest
    }

    def save(){
        print request.JSON
        responseOfRequest = teamService.saveData(request.JSON)
        respond responseOfRequest
    }

    def update(){
        print request.JSON
        responseOfRequest = teamService.updateData(request.JSON)
        respond responseOfRequest
    }

    def delete(){
        print request.JSON
        responseOfRequest = teamService.deleteData(request.JSON)
        respond responseOfRequest
    }
}

