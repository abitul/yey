package apifutsal

class ImageController {

    static responseFormats = ['json']

    ImageService imageService
    def responseOfRequest

    def show(params) { 
        print params
        responseOfRequest = imageService.showData(params)
        respond responseOfRequest
    }

    def save(){
        print request.JSON
        responseOfRequest = imageService.saveData(request.JSON)
        respond responseOfRequest
    }

    def update(){
        print request.JSON
        responseOfRequest = imageService.updateData(request.JSON)
        respond responseOfRequest
    }

    def delete(){
        print request.JSON
        responseOfRequest = imageService.deleteData(request.JSON)
        respond responseOfRequest
    }
}
