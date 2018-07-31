package apifutsal

class ScheduleController {

    static responseFormats = ['json']     
    static allowedMethods = [save: "POST", show: "GET", update: "PUT", delete: "DELETE"]

    ScheduleService scheduleService
    def responseOfRequest

    def show(params) { 
        print params
        responseOfRequest = scheduleService.showData(params)
        respond responseOfRequest
    }

    def save(){
        print request.JSON
        responseOfRequest = scheduleService.saveData(request.JSON)
        respond responseOfRequest
    }

    def update(){
        print request.JSON
        responseOfRequest = scheduleService.updateData(request.JSON)
        respond responseOfRequest
    }

    def delete(){
        print request.JSON
        responseOfRequest = scheduleService.deleteData(request.JSON)
        respond responseOfRequest
    }
}
