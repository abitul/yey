package apifutsal

class DisclaimerController {

    static responseFormats = ['json']

    DisclaimerService disclaimerService
    def responseOfRequest

    def show(params) { 
        print params
        responseOfRequest = disclaimerService.showData(params)
        respond responseOfRequest
    }

    def save(){
        print request.JSON
        responseOfRequest = disclaimerService.saveData(request.JSON)
        respond responseOfRequest
    }

    def update(){
        print request.JSON
        responseOfRequest = disclaimerService.updateData(request.JSON)
        respond responseOfRequest
    }

    def delete(){
        print request.JSON
        responseOfRequest = disclaimerService.deleteData(request.JSON)
        respond responseOfRequest
    }
}
