package apifutsal

import grails.transaction.Transactional

@Transactional
class BookingService {
    
    def result 
    def createdDate = new Date()

    
    def showData(params) {
        try{
            print createdDate
            Integer offset = (params.int("page")-1) * params.int("max")
            result = params.searchValue == "" ? Booking.listOrderByCreatedDate(order: "desc") : Booking.findAllByBookingCodeIlike("%${params.searchValue}%",[max: params.int("max"), sort: "bookingCode", order: "desc", offset: offset])
        }catch(e){
            print "error gettting data"
            print e
            result = [message: "failed get data booking"]
        }

        return result
    }

    def saveData(params) {
        try{
            def booking = new Booking()
            print createdDate
            booking.startTime = params.startTime
            booking.endTime = params.endTime
            booking.status = params.status
            booking.bookingCode = params.bookingCode
            booking.createdDate = createdDate
            booking.save(flush: true, failOnError: true)
            result = [message: "success insert data"]
        }catch(e){
            print "error saving data"
            print e
            result = [message: "failed save data booking"]
        }

        return result
    }

    def updateData(params) {
        try{
            def booking = Booking.get(params.id)
            print booking
            booking.startTime = params.startTime
            booking.endTime = params.endTime
            booking.status = params.status
            booking.bookingCode = params.bookingCode
            booking.createdDate = createdDate
            booking.save(flush: true, failOnError: true)
            result = [message: "success update data"]
        }catch(e){
            print "error updating data"
            print e
            result = [message: "failed update data booking"]
        }

        return result
    }

    def deleteData(params) {
        try{
            def booking = Booking.get(params.id)
            booking.delete()
            result = [message: "success delete"]
        }catch(e){
            print "error deleting data"
            print e
            result = [message: "failed delete data booking"]
        }

        return result
    }
}
