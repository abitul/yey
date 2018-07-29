package apifutsal

class NotificationController {

    static responseFormats = ['json']

    NotificationService notificationService
    def responseOfRequest

    def show(params) { 
        print params
        responseOfRequest = notificationService.showData(params)
        respond responseOfRequest
    }

    def save(){
        print request.JSON
        responseOfRequest = notificationService.saveData(request.JSON)
        respond responseOfRequest
    }

    def update(){
        print request.JSON
        responseOfRequest = notificationService.updateData(request.JSON)
        respond responseOfRequest
    }

    def delete(){
        print request.JSON
        responseOfRequest = notificationService.deleteData(request.JSON)
        respond responseOfRequest
    }
}
