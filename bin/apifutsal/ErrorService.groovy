package apifutsal

import grails.transaction.Transactional

@Transactional
class ErrorService {

    def result 
    def lastUpdate = new Date()
    def error
    ErrorHandler errorHandler

    def showData(params) {

        try{
            print lastUpdate
            if(params.errorId){
                error = Error.get(params.errorId as Integer)
            }else{
                Integer offset = (params.int("page")-1) * params.int("max")
                error = params.searchValue ? Error.findAllByTableNameIlike("%${params.searchValue}%",[max: params.int("max"), sort: "tableName", order: "desc", offset: offset]) : Error.listOrderByLastUpdate(order: "desc") 
            }

            result = [
                data : error,
                message : "success get data",
                isSuccessFull : true
            ]

        }catch(e){
            result = errorHandler.errorChecking(null, "ERROR_GET_DATA", "Failed get Data error", e, "error")
        }

        return result

    }

    def saveData(params) {

        try{
            error = new Error()
            print lastUpdate
            error.errorTag = params.errorTag
            error.errorMessage = params.errorMessage
            error.errorField = params.errorField
            error.tableName = params.tableName
            error.lastUpdate = lastUpdate
            error.save(flush: true, failOnError: true)
            result = [message: "success insert data", isSuccessFull : true]
        }catch(e){
            result = errorHandler.errorChecking(team, "ERROR_SAVE_DATA", "Error save data", e, "error")
        }

        return result

    }

    def updateData(params) {

        try{
            error = Error.get(params.id)
            print error
            error.errorTag = params.errorTag
            error.errorMessage = params.errorMessage
            error.errorField = params.errorField
            error.tableName = params.tableName
            error.lastUpdate = lastUpdate
            error.save(flush: true, failOnError: true)
            result = [message: "success update data", isSuccessFull : true]
        }catch(e){
            result = errorHandler.errorChecking(team, "ERROR_UPDATE_DATA", "Failed update data error", e, "error")
        }

        return result

    }

    def deleteData(params) {

        try{
            error = Error.get(params.id)
            error.delete()
            result = [message: "success delete", isSuccessFull : true]
        }catch(e){
            result = errorHandler.errorChecking(null, "ERROR_DELETE_DATA", "Failed delete data error", e, "error")
        }

        return result
        
    }
}
