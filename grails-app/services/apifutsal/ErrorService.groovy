package apifutsal

import grails.transaction.Transactional

@Transactional
class ErrorService {

    def result 
    def lastUpdate = new Date()

    def showData(params) {
        try{
            print lastUpdate
            if(params.errorId){
                def error = Disclaimer.get(params.errorId as Integer)
                result = Error.findAllByDisclaimer(error)
            }else{
                Integer offset = (params.int("page")-1) * params.int("max")
                result = params.searchValue == "" ? Error.listOrderByLastUpdate(order: "desc") : Error.findAllByErrorNameIlike("%${params.searchValue}%",[max: params.int("max"), sort: "errorName", order: "desc", offset: offset])
            }
        }catch(e){
            print "error gettting data"
            print e
            result = [message: "failed get data error"]
        }

        return result
    }

    def saveData(params) {
        try{
            def error = new Error()
            print lastUpdate
            error.errorTag = params.errorTag
            error.errorMessage = params.errorMessage
            error.errorField = params.errorField
            error.tableName = params.tableName
            error.lastUpdate = lastUpdate
            error.save(flush: true, failOnError: true)
            result = [message: "success insert data"]
        }catch(e){
            print "error saving data"
            print e
            result = [message: "failed save data error"]
        }

        return result
    }

    def updateData(params) {
        try{
            def error = Error.get(params.id)
            print error
            error.errorTag = params.errorTag
            error.errorMessage = params.errorMessage
            error.errorField = params.errorField
            error.tableName = params.tableName
            error.lastUpdate = lastUpdate
            error.save(flush: true, failOnError: true)
            result = [message: "success update data"]
        }catch(e){
            print "error updating data"
            print e
            result = [message: "failed update data error"]
        }

        return result
    }

    def deleteData(params) {
        try{
            def error = Error.get(params.id)
            error.delete()
            result = [message: "success delete"]
        }catch(e){
            print "error deleting data"
            print e
            result = [message: "failed delete data error"]
        }

        return result
    }
}
