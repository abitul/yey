package apifutsal

class MatchRecordController {

    static responseFormats = ['json']     
    static allowedMethods = [save: "POST", show: "GET", update: "PUT", delete: "DELETE"]

    MatchRecordService matchRecordService
    def responseOfRequest

    def show(params) { 
        print params
        responseOfRequest = matchRecordService.showData(params)
        respond responseOfRequest
    }

    def save(){
        print request.JSON
        responseOfRequest = matchRecordService.saveData(request.JSON)
        respond responseOfRequest
    }

    def update(){
        print request.JSON
        responseOfRequest = matchRecordService.updateData(request.JSON)
        respond responseOfRequest
    }

    def delete(){
        print request.JSON
        responseOfRequest = matchRecordService.deleteData(request.JSON)
        respond responseOfRequest
    }
}
