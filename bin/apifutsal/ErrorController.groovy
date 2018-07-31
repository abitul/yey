package apifutsal

class ErrorController {

    static responseFormats = ['json']     
    static allowedMethods = [save: "POST", show: "GET", update: "PUT", delete: "DELETE"]

    ErrorService errorService
    def responseOfRequest

    def show(params) { 
        print params
        responseOfRequest = errorService.showData(params)
        respond responseOfRequest
    }

    def save(){
        print request.JSON
        responseOfRequest = errorService.saveData(request.JSON)
        respond responseOfRequest
    }

    def update(){
        print request.JSON
        responseOfRequest = errorService.updateData(request.JSON)
        respond responseOfRequest
    }

    def delete(){
        print request.JSON
        responseOfRequest = errorService.deleteData(request.JSON)
        respond responseOfRequest
    }
}
