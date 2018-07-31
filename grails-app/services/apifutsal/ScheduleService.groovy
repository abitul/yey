package apifutsal

import grails.transaction.Transactional

@Transactional
class ScheduleService {

    def result 
    def lastUpdate = new Date()
    def schedule

    def showData(params) {

        try{
            println lastUpdate
           // find by stadion name
            def stadion = Stadion.get(params.stadionId as Integer)
            def listFutsalField = FutsalField.findAllByStadion(stadion)
            def startTime = Date.parse("yyyy-MM-dd H:mm:s", params.startTime)
            def endTime = Date.parse("yyyy-MM-dd H:mm:s", params.endTime)
            schedule = []
            listFutsalField.each{res->
                    def listBookingOfFutsalField = Booking.findAllByFutsalFieldIdAndStartTimeAndEndTime(res.id, startTime , endTime)
                    println "mantap gann..."
                    println listBookingOfFutsalField?.empty
                    def status = listBookingOfFutsalField ? false : true
                    def objectData = [ 
                                        futsalFieldId: res.id,
                                        futsalFieldName : res.name,
                                        type : res.type,
                                        startTime : params.startTime,
                                        endTime : params.endTime,
                                        stadionId : params.stadionId,
                                        price: res.price,
                                        isReady: status ]
                    schedule.push(objectData) 
            }

            result = [
                data : schedule,
                message : "success get data",
                isSuccessFull : true
            ]

        }catch(e){
            result = errorHandler.errorChecking(null, "ERROR_GET_DATA", "Failed get Data Team", e, "team")
        }

        return result
        
    }
}
