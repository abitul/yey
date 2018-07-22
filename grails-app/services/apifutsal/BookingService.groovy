package apifutsal

import grails.transaction.Transactional

@Transactional
class BookingService {
    
    def result 
    def createdDate = new Date()
    RandomGenerator randomGenerator
    
    def showData(params) {
        try{
            print createdDate
            if(params.teamId){
                def team = Team.get(params.teamId as Integer)
                result = Booking.findAllByTeam(team)
            }else if(params.stadionId){
                result = Booking.findAllByStadionId(params.stadionId)
            }else{
                Integer offset = (params.int("page")-1) * params.int("max")
                result = params.searchValue == "" ? Booking.listOrderByCreatedDate(order: "desc") : Booking.findAllByBookingCodeIlike("%${params.searchValue}%",[max: params.int("max"), sort: "bookingCode", order: "desc", offset: offset])
            }
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
            booking.startTime =  Date.parse("yyyy-MM-dd H:mm:s", params.startTime)
            booking.endTime = Date.parse("yyyy-MM-dd H:mm:s", params.endTime)
            booking.status = params.status
            booking.stadionId = params.stadionId
            booking.futsalFieldId = params.futsalFieldId
            booking.versusTeamId = params.versusTeamId ? params.versusTeamId : null
            booking.bookingCode = randomGenerator.generator( (('A'..'Z')).join(), 6)
            booking.duration = params.duration
            booking.bookingFee = params.bookingFee
            booking.createdDate = createdDate
            def team = Team.get(params.teamId)
            team.addToBookings(booking).save(flush: true, failOnError: true)
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
            if(params.isWannaToBattle){
                booking.versusTeamId = params.versusTeamId
            }else{
                booking.startTime =  Date.parse("yyyy-MM-dd H:mm:s", params.startTime)
                booking.endTime = Date.parse("yyyy-MM-dd H:mm:s", params.endTime)
                booking.status = params.status
                booking.stadionId = params.stadionId
                booking.futsalFieldId = params.futsalFieldId
                booking.versusTeamId = params.versusTeamId ? params.versusTeamId : null
                booking.bookingCode = params.bookingCode
                booking.duration = params.duration
                booking.bookingFee = params.bookingFee
                booking.createdDate = createdDate
            }

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
