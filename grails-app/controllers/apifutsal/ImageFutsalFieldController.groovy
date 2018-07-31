package apifutsal

class ImageFutsalFieldController {

    static responseFormats = ['json']     
    static allowedMethods = [save: "POST", show: "GET", update: "PUT", delete: "DELETE"]

    ImageFutsalFieldService imageFutsalFieldService
    def responseOfRequest

    def show(params) { 
        print params
        responseOfRequest = imageFutsalFieldService.showData(params)
        respond responseOfRequest
    }

    def save(){
        print request.JSON
        responseOfRequest = imageFutsalFieldService.saveData(request.JSON)
        respond responseOfRequest
    }

    def update(){
        print request.JSON
        responseOfRequest = imageFutsalFieldService.updateData(request.JSON)
        respond responseOfRequest
    }

    def delete(){
        print request.JSON
        responseOfRequest = imageFutsalFieldService.deleteData(request.JSON)
        respond responseOfRequest
    }
}
