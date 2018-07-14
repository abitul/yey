package apifutsal

class BookingController {

    static responseFormats = ['json']

    BookingService bookingService
    def responseOfRequest

    def show(params) { 
        print params
        responseOfRequest = bookingService.showData(params)
        respond responseOfRequest
    }

    def save(){
        print request.JSON
        responseOfRequest = bookingService.saveData(request.JSON)
        respond responseOfRequest
    }

    def update(){
        print request.JSON
        responseOfRequest = bookingService.updateData(request.JSON)
        respond responseOfRequest
    }

    def delete(){
        print request.JSON
        responseOfRequest = bookingService.deleteData(request.JSON)
        respond responseOfRequest
    }
}
