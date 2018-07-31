package apifutsal

class DeviceNotificationController {

 
    static responseFormats = ['json']     
    static allowedMethods = [save: "POST", show: "GET", update: "PUT", delete: "DELETE"]

    DeviceNotificationService deviceNotificationService
    def responseOfRequest

    def show(params) { 
        print params
        responseOfRequest = deviceNotificationService.showData(params)
        respond responseOfRequest
    }

    def save(){
        print request.JSON
        responseOfRequest = deviceNotificationService.saveData(request.JSON)
        respond responseOfRequest
    }

    def update(){
        print request.JSON
        responseOfRequest = deviceNotificationService.updateData(request.JSON)
        respond responseOfRequest
    }

    def delete(){
        print request.JSON
        responseOfRequest = deviceNotificationService.deleteData(request.JSON)
        respond responseOfRequest
    }
}
