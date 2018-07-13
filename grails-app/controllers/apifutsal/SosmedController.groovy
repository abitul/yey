package apifutsal

class SosmedController {

    static responseFormats = ['json']

    SosmedService sosmedService
    def responseOfRequest

    def show(params) { 
        print params
        responseOfRequest = sosmedService.showData(params)
        respond responseOfRequest
    }

    def save(){
        print request.JSON
        responseOfRequest = sosmedService.saveData(request.JSON)
        respond responseOfRequest
    }

    def update(){
        print request.JSON
        responseOfRequest = sosmedService.updateData(request.JSON)
        respond responseOfRequest
    }

    def delete(){
        print request.JSON
        responseOfRequest = sosmedService.deleteData(request.JSON)
        respond responseOfRequest
    }
}
