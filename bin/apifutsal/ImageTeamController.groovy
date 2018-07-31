package apifutsal

class ImageTeamController {

    static responseFormats = ['json']     
    static allowedMethods = [save: "POST", show: "GET", update: "PUT", delete: "DELETE"]

    ImageTeamService imageTeamService
    def responseOfRequest

    def show(params) { 
        print params
        responseOfRequest = imageTeamService.showData(params)
        respond responseOfRequest
    }

    def save(){
        print request.JSON
        responseOfRequest = imageTeamService.saveData(request.JSON)
        respond responseOfRequest
    }

    def update(){
        print request.JSON
        responseOfRequest = imageTeamService.updateData(request.JSON)
        respond responseOfRequest
    }

    def delete(){
        print request.JSON
        responseOfRequest = imageTeamService.deleteData(request.JSON)
        respond responseOfRequest
    }}
