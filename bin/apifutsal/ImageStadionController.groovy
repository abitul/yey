package apifutsal

class ImageStadionController {

    static responseFormats = ['json']

    ImageStadionService imageStadionService
    def responseOfRequest

    def show(params) { 
        print params
        responseOfRequest = imageStadionService.showData(params)
        respond responseOfRequest
    }

    def save(){
        print request.JSON
        responseOfRequest = imageStadionService.saveData(request.JSON)
        respond responseOfRequest
    }

    def update(){
        print request.JSON
        responseOfRequest = imageStadionService.updateData(request.JSON)
        respond responseOfRequest
    }

    def delete(){
        print request.JSON
        responseOfRequest = imageStadionService.deleteData(request.JSON)
        respond responseOfRequest
    }
}
