package apifutsal

import grails.transaction.Transactional

@Transactional
class DisclaimerService {

    def result 
    def lastUpdate = new Date()
    def disclaimer
    ErrorHandler errorHandler
    
    def showData(params) {

        try{
            print lastUpdate
            if(params.disclaimerId){
               disclaimer = Disclaimer.get(params.disclaimerId as Integer)
            }else{
                Integer offset = (params.int("page")-1) * params.int("max")
                disclaimer = params.searchValue ? Disclaimer.findAllByDisclaimerNameIlike("%${params.searchValue}%",[max: params.int("max"), sort: "disclaimerName", order: "desc", offset: offset]) :  Disclaimer.listOrderByLastUpdate(order: "desc") 
            }

            result = [ 
                    data : disclaimer,
                    message : "success get data",
                    isSuccessFull : true 
            ] 

        }catch(e){
            result = errorHandler.errorChecking(null, "ERROR_GET_DATA", "Failed get Data disclaimer", e, "disclaimer")
        }

        return result

    }

    def saveData(params) {

        try{
            disclaimer = new Disclaimer()
            print lastUpdate
            disclaimer.dislcaimerTag = params.dislcaimerTag
            disclaimer.disclaimerMessage = params.disclaimerMessage
            disclaimer.module = params.module
            disclaimer.enabled = params.enabled
            disclaimer.lastUpdate = lastUpdate
            disclaimer.save(flush: true, failOnError: true)
            result = [message: "success insert data", isSuccessFull : true]
        }catch(e){
            result = errorHandler.errorChecking(team, "ERROR_SAVE_DATA", "Error save data", e, "disclaimer")
        }

        return result

    }

    def updateData(params) {

        try{
            disclaimer = Disclaimer.get(params.id)
            print disclaimer
            disclaimer.dislcaimerTag = params.dislcaimerTag
            disclaimer.disclaimerMessage = params.disclaimerMessage
            disclaimer.module = params.module
            disclaimer.enabled = params.enabled
            disclaimer.lastUpdate = lastUpdate
            disclaimer.save(flush: true, failOnError: true)
            result = [message: "success update data", isSuccessFull : true]
        }catch(e){
            result = errorHandler.errorChecking(team, "ERROR_UPDATE_DATA", "Failed update data disclaimer", e, "disclaimer")
        }

        return result

    }

    def deleteData(params) {

        try{
            disclaimer = Disclaimer.get(params.id)
            disclaimer.delete()
            result = [message: "success delete", isSuccessFull : true]
        }catch(e){
            result = errorHandler.errorChecking(null, "ERROR_DELETE_DATA", "Failed delete data disclaimer", e, "disclaimer")
        }

        return result
        
    }
}
