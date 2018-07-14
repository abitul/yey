package apifutsal

class ReviewController {

    static responseFormats = ['json']

    ReviewService reviewService
    def responseOfRequest

    def show(params) { 
        print params
        responseOfRequest = reviewService.showData(params)
        respond responseOfRequest
    }

    def save(){
        print request.JSON
        responseOfRequest = reviewService.saveData(request.JSON)
        respond responseOfRequest
    }

    def update(){
        print request.JSON
        responseOfRequest = reviewService.updateData(request.JSON)
        respond responseOfRequest
    }

    def delete(){
        print request.JSON
        responseOfRequest = reviewService.deleteData(request.JSON)
        respond responseOfRequest
    }
}
