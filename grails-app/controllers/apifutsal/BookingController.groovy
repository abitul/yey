package apifutsal

class BookingController {

    static responseFormats = ['json']

    FutsalfieldService futsalfieldService
    def responseOfRequest

    def show(params) { 
        print params
        responseOfRequest = futsalfieldService.showData(params)
        respond responseOfRequest
    }

    def save(){
        print request.JSON
        responseOfRequest = futsalfieldService.saveData(request.JSON)
        respond responseOfRequest
    }

    def update(){
        print request.JSON
        responseOfRequest = futsalfieldService.updateData(request.JSON)
        respond responseOfRequest
    }

    def delete(){
        print request.JSON
        responseOfRequest = futsalfieldService.deleteData(request.JSON)
        respond responseOfRequest
    }
}
