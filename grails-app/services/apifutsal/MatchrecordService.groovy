package apifutsal

import grails.transaction.Transactional

@Transactional
class MatchRecordService {

    def result 
    def createdDate = new Date()

    def showData(params) {
        try{
            print createdDate
            Integer offset = (params.int("page")-1) * params.int("max")
            result = params.searchValue == "" ? MatchRecord.listOrderByCreatedDate(order: "desc") : MatchRecord.findAllByStatusIlike("%${params.searchValue}%",[max: params.int("max"), sort: "status", order: "desc", offset: offset])
        }catch(e){
            print "error gettting data"
            print e
            result = [message: "failed get data matchRecord"]
        }

        return result
    }

    def saveData(params) {
        try{
            def matchRecord = new MatchRecord()
            print createdDate
            matchRecord.status = params.status
            matchRecord.score = params.score
            matchRecord.createdDate = params.createdDate
            matchRecord.save(flush: true, failOnError: true)
            result = [message: "success insert data"]
        }catch(e){
            print "error saving data"
            print e
            result = [message: "failed save data matchRecord"]
        }

        return result
    }

    def updateData(params) {
        try{
            def matchRecord = MatchRecord.get(params.id)
            print matchRecord
            matchRecord.status = params.status
            matchRecord.createdDate = params.createdDate
            matchRecord.save(flush: true, failOnError: true)
            result = [message: "success update data"]
        }catch(e){
            print "error updating data"
            print e
            result = [message: "failed update data matchRecord"]
        }

        return result
    }

    def deleteData(params) {
        try{
            def matchRecord = MatchRecord.get(params.id)
            matchRecord.delete()
            result = [message: "success delete"]
        }catch(e){
            print "error deleting data"
            print e
            result = [message: "failed delete data matchRecord"]
        }

        return result
    }
}
