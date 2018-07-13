package apifutsal

import grails.transaction.Transactional


@Transactional
class StadionService {

    def result 
    def lastUpdate = new Date()

    def showData(params) {
        try{
            print lastUpdate
            Integer offset = (params.int("page")-1) * params.int("max")
            result = params.searchValue == "" ? Stadion.listOrderByLastUpdate(order: "desc") : Stadion.findAllByStadionNameIlike("%"+params.searchValue+"%",[max: params.int("max"), sort: "stadionName", order: "desc", offset: offset])
        }catch(e){
            print "error gettting data"
            print e
            result = [message: "failed get data stadion"]
        }

        return result
    }

    def saveData(params) {
        try{
            def stadion = new Stadion()
            print lastUpdate
            stadion.stadionName = params.stadionName
            stadion.location = params.location
            stadion.contactNo = params.contactNo
            stadion.owner = params.owner
            stadion.lastUpdate = lastUpdate
            stadion.save(flush: true, failOnError: true)
            result = [message: "success insert data"]
        }catch(e){
            print "error saving data"
            print e
            result = [message: "failed save data stadion"]
        }

        return result
    }

    def updateData(params) {
        try{
            def stadion = Stadion.get(params.id)
            print stadion
            stadion.stadionName = params.stadionName
            stadion.location = params.location
            stadion.contactNo = params.contactNo
            stadion.owner = params.owner
            stadion.lastUpdate = lastUpdate
            stadion.save(flush: true, failOnError: true)
            result = [message: "success update data"]
        }catch(e){
            print "error updating data"
            print e
            result = [message: "failed update data stadion"]
        }

        return result
    }

    def deleteData(params) {
        try{
            def stadion = Stadion.get(params.id)
            stadion.delete()
            result = [message: "success delete"]
        }catch(e){
            print "error deleting data"
            print e
            result = [message: "failed delete data stadion"]
        }

        return result
    }
}

