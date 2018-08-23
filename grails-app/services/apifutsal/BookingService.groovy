package apifutsal

import grails.transaction.Transactional

@Transactional
class BookingService {
    
    def result 
    def createdDate = new Date()
    def booking
    RandomGenerator randomGenerator
    ErrorHandler errorHandler

    def showData(params) {

        try{
            print createdDate
            if(params.teamId){
                def team = Team.get(params.teamId as Integer)
                booking = Booking.findAllByTeam(team)
            }else if(params.stadionId){
                booking = Booking.findAllByStadionId(params.stadionId)
            }else{
                Integer offset = (params.int("page")-1) * params.int("max")
                booking = params.searchValue ? Booking.findAllByBookingCodeIlike("%${params.searchValue}%",[max: params.int("max"), sort: "bookingCode", order: "desc", offset: offset]) : Booking.listOrderByCreatedDate(order: "desc") 
            }

            result = [
                data : booking,
                message : "success get data",
                isSuccessFull : true
            ]
            
        }catch(e){
            result = errorHandler.errorChecking(null, "ERROR_GET_DATA", "Failed get Data Booking", e, "booking")
        }

        return result

    }

    def saveData(params) {

        try{
            booking = new Booking()
            print createdDate
            booking.startTime =  Date.parse("yyyy-MM-dd H:mm:s", params.startTime)
            booking.endTime = Date.parse("yyyy-MM-dd H:mm:s", params.endTime)
            booking.status = params.status
            booking.stadionId = params.stadionId
            booking.versusTeamId = params.versusTeamId ? params.versusTeamId : null
            booking.bookingCode = randomGenerator.generator( (('A'..'Z')).join(), 6)
            booking.duration = params.duration
            booking.bookingFee = params.bookingFee
            booking.createdDate = createdDate
            def futsalField = FutsalField.get(params.futsalFieldId)
            futsalField.addToBookings(booking).save(flush: true, failOnError: true)
            result = [message: "success insert data", isSuccessFull : true]
        }catch(e){
            result = errorHandler.errorChecking(booking, "ERROR_SAVE_DATA", "Error save data", e, "booking")
        }

        return result

    }

    def updateData(params) {

        try{
            booking = Booking.get(params.id)
            print booking
            if(params.isWannaToBattle){
                booking.versusTeamId = params.versusTeamId
            }else{
                booking.startTime =  Date.parse("yyyy-MM-dd H:mm:s", params.startTime)
                booking.endTime = Date.parse("yyyy-MM-dd H:mm:s", params.endTime)
                booking.status = params.status
                booking.stadionId = params.stadionId
                booking.versusTeamId = params.versusTeamId ? params.versusTeamId : null
                booking.bookingCode = params.bookingCode
                booking.duration = params.duration
                booking.bookingFee = params.bookingFee
                booking.createdDate = createdDate
            }

            booking.save(flush: true, failOnError: true)
            result = [message: "success update data", isSuccessFull : true]
        }catch(e){
            result = errorHandler.errorChecking(booking, "ERROR_UPDATE_DATA", "Failed update data booking", e, "booking")
        }

        return result

    }

    def deleteData(params) {

        try{
            booking = Booking.get(params.id)
            booking.delete()
            result = [message: "success delete", isSuccessFull : true]
        }catch(e){
            result = errorHandler.errorChecking(null, "ERROR_DELETE_DATA", "Failed delete data booking", e, "booking")
        }

        return result
        
    }
}
