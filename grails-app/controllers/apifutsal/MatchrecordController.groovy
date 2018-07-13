package apifutsal

class MatchrecordController {

    static responseFormats = ['json']

    MatchrecordService matchrecordService
    def responseOfRequest

    def show(params) { 
        print params
        responseOfRequest = matchrecordService.showData(params)
        respond responseOfRequest
    }

    def save(){
        print request.JSON
        responseOfRequest = matchrecordService.saveData(request.JSON)
        respond responseOfRequest
    }

    def update(){
        print request.JSON
        responseOfRequest = matchrecordService.updateData(request.JSON)
        respond responseOfRequest
    }

    def delete(){
        print request.JSON
        responseOfRequest = matchrecordService.deleteData(request.JSON)
        respond responseOfRequest
    }
}
