package apifutsal

import grails.transaction.Transactional

@Transactional
class ScheduleService {

    def result 
    def lastUpdate = new Date()

    def showData(params) {
        try{
            println lastUpdate
           // find by stadion name
            def stadion = Stadion.get(params.stadionId as Integer)
            def listFutsalField = FutsalField.findAllByStadion(stadion)
            def startTime = Date.parse("yyyy-MM-dd H:mm:s", params.startTime)
            def endTime = Date.parse("yyyy-MM-dd H:mm:s", params.endTime)
            result = []
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
                    result.push(objectData) 
            }
        }catch(e){
            print "error gettting data"
            print e
            result = [message: "failed get data schedule"]
        }

        return result
    }
}
