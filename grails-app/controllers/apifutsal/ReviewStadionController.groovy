package apifutsal

class ReviewStadionController {

    static responseFormats = ['json']

    ReviewStadionService reviewStadionService
    def responseOfRequest

    def show(params) { 
        print params
        responseOfRequest = reviewStadionService.showData(params)
        respond responseOfRequest
    }

    def save(){
        print request.JSON
        responseOfRequest = reviewStadionService.saveData(request.JSON)
        respond responseOfRequest
    }

    def update(){
        print request.JSON
        responseOfRequest = reviewStadionService.updateData(request.JSON)
        respond responseOfRequest
    }

    def delete(){
        print request.JSON
        responseOfRequest = reviewStadionService.deleteData(request.JSON)
        respond responseOfRequest
    }
}
