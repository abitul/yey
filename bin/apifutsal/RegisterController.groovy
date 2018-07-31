package apifutsal

class RegisterController {

    static responseFormats = ['json']     
    static allowedMethods = [save: "POST", show: "GET", update: "PUT", delete: "DELETE"]
    
    RegisterService registerService
    def responseOfRequest

    def show(params) { 
        print params
        responseOfRequest = registerService.showData(params)
        respond responseOfRequest
    }

    def save(){
        print request.JSON
        responseOfRequest = registerService.saveData(request.JSON)
        respond responseOfRequest
    }

    def update(){
        print request.JSON
        responseOfRequest = registerService.updateData(request.JSON)
        respond responseOfRequest
    }

    def delete(){
        print request.JSON
        responseOfRequest = registerService.deleteData(request.JSON)
        respond responseOfRequest
    }
}
