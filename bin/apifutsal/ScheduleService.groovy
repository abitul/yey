package apifutsal

import grails.transaction.Transactional

@Transactional
class ScheduleService {

    def result 
    def lastUpdate = new Date()

    def showData(params) {
        try{
            print lastUpdate
            Integer offset = (params.int("page")-1) * params.int("max")
            result = params.searchValue == "" ? Schedule.listOrderByLastUpdate(order: "desc") : Schedule.findAllByScheduleNameIlike("%${params.searchValue}%",[max: params.int("max"), sort: "scheduleName", order: "desc", offset: offset])
        }catch(e){
            print "error gettting data"
            print e
            result = [message: "failed get data schedule"]
        }

        return result
    }

    def saveData(params) {
        try{
            def schedule = new Schedule()
            print lastUpdate
            schedule.scheduleName = params.scheduleName
            schedule.typeSchedule = params.typeSchedule
            schedule.lastUpdate = lastUpdate
            schedule.save(flush: true, failOnError: true)
            result = [message: "success insert data"]
        }catch(e){
            print "error saving data"
            print e
            result = [message: "failed save data schedule"]
        }

        return result
    }

    def updateData(params) {
        try{
            def schedule = Schedule.get(params.id)
            print schedule
            schedule.scheduleName = params.scheduleName
            schedule.typeSchedule = params.typeSchedule
            schedule.lastUpdate = lastUpdate
            schedule.save(flush: true, failOnError: true)
            result = [message: "success update data"]
        }catch(e){
            print "error updating data"
            print e
            result = [message: "failed update data schedule"]
        }

        return result
    }

    def deleteData(params) {
        try{
            def schedule = Schedule.get(params.id)
            schedule.delete()
            result = [message: "success delete"]
        }catch(e){
            print "error deleting data"
            print e
            result = [message: "failed delete data schedule"]
        }

        return result
    }
}
