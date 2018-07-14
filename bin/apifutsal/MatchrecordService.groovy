package apifutsal

import grails.transaction.Transactional

@Transactional
class MatchrecordService {

    def result 
    def lastUpdate = new Date()

    def showData(params) {
        try{
            print lastUpdate
            Integer offset = (params.int("page")-1) * params.int("max")
            result = params.searchValue == "" ? Matchrecord.listOrderByLastUpdate(order: "desc") : Matchrecord.findAllByMatchrecordNameIlike("%${params.searchValue}%",[max: params.int("max"), sort: "matchrecordName", order: "desc", offset: offset])
        }catch(e){
            print "error gettting data"
            print e
            result = [message: "failed get data matchrecord"]
        }

        return result
    }

    def saveData(params) {
        try{
            def matchrecord = new Matchrecord()
            print lastUpdate
            matchrecord.matchrecordName = params.matchrecordName
            matchrecord.typeMatchrecord = params.typeMatchrecord
            matchrecord.lastUpdate = lastUpdate
            matchrecord.save(flush: true, failOnError: true)
            result = [message: "success insert data"]
        }catch(e){
            print "error saving data"
            print e
            result = [message: "failed save data matchrecord"]
        }

        return result
    }

    def updateData(params) {
        try{
            def matchrecord = Matchrecord.get(params.id)
            print matchrecord
            matchrecord.matchrecordName = params.matchrecordName
            matchrecord.typeMatchrecord = params.typeMatchrecord
            matchrecord.lastUpdate = lastUpdate
            matchrecord.save(flush: true, failOnError: true)
            result = [message: "success update data"]
        }catch(e){
            print "error updating data"
            print e
            result = [message: "failed update data matchrecord"]
        }

        return result
    }

    def deleteData(params) {
        try{
            def matchrecord = Matchrecord.get(params.id)
            matchrecord.delete()
            result = [message: "success delete"]
        }catch(e){
            print "error deleting data"
            print e
            result = [message: "failed delete data matchrecord"]
        }

        return result
    }
}
