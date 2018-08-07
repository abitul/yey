package apifutsal

import grails.transaction.Transactional

@Transactional
class ScheduleService {

    def result 
    def lastUpdate = new Date()
    def schedule
    ErrorHandler errorHandler

    def showData(params) {

        try{

            println lastUpdate
            schedule = []
            def stadion
            Integer offset = (params.int("page")-1) * params.int("max")
            if(params.stadionId){
                stadion = Stadion.get(params.stadionId as Integer)
            }else{
                stadion = Stadion.findAllByStadionNameIlikeOrProvinceIlikeOrDistricsIlikeOrSubDistricsIlikesOrKelurahanIlike("%${params.searchValue}%","%${params.searchValue}%","%${params.searchValue}%","%${params.searchValue}%","%${params.searchValue}%",[max: params.int("max"), sort: "stadionName", order: "desc", offset: offset])
            }
                   
            stadion.each{stadionData->
                def listFutsalField = FutsalField.findAllByStadion(stadionData)
                def startTime = Date.parse("yyyy-MM-dd H:mm:s", params.startTime)
                def endTime = Date.parse("yyyy-MM-dd H:mm:s", params.endTime)
                listFutsalField.each{res->
                        def stadionId = params.stadionId? params.stadionId : res.stadionId
                        def stadionName = Stadion.get(stadionId)?.stadionName
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
                                            stadionId : params.stadionId? params.stadionId : res.stadionId,
                                            stadionName : stadionName,
                                            price: res.price,
                                            isReady: status ]
                        schedule.push(objectData) 
                }
            }

            result = [
                data : schedule,
                message : "success get data",
                isSuccessFull : true
            ]

        }catch(e){
            result = errorHandler.errorChecking(null, "ERROR_GET_DATA", "Failed get Data Schedule", e, "Schedule")
        }

        return result
        
    }
}
