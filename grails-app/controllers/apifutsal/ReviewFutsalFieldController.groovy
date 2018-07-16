package apifutsal

class ReviewFutsalFieldController {

    static responseFormats = ['json']

    ReviewFutsalFieldService reviewFutsalFieldService
    def responseOfRequest

    def show(params) { 
        print params
        responseOfRequest = reviewFutsalFieldService.showData(params)
        respond responseOfRequest
    }

    def save(){
        print request.JSON
        responseOfRequest = reviewFutsalFieldService.saveData(request.JSON)
        respond responseOfRequest
    }

    def update(){
        print request.JSON
        responseOfRequest = reviewFutsalFieldService.updateData(request.JSON)
        respond responseOfRequest
    }

    def delete(){
        print request.JSON
        responseOfRequest = reviewFutsalFieldService.deleteData(request.JSON)
        respond responseOfRequest
    }
}
