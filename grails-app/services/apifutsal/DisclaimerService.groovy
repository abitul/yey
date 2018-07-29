package apifutsal

import grails.transaction.Transactional

@Transactional
class DisclaimerService {

    def result 
    def lastUpdate = new Date()

    def showData(params) {
        try{
            print lastUpdate
            if(params.disclaimerId){
                def disclaimer = Disclaimer.get(params.disclaimerId as Integer)
                result = Disclaimer.findAllByDisclaimer(disclaimer)
            }else{
                Integer offset = (params.int("page")-1) * params.int("max")
                result = params.searchValue == "" ? Disclaimer.listOrderByLastUpdate(order: "desc") : Disclaimer.findAllByDisclaimerNameIlike("%${params.searchValue}%",[max: params.int("max"), sort: "disclaimerName", order: "desc", offset: offset])
            }
        }catch(e){
            print "error gettting data"
            print e
            result = [message: "failed get data disclaimer"]
        }

        return result
    }

    def saveData(params) {
        try{
            def disclaimer = new Disclaimer()
            print lastUpdate
            disclaimer.dislcaimerTag = params.dislcaimerTag
            disclaimer.disclaimerMessage = params.disclaimerMessage
            disclaimer.module = params.module
            disclaimer.enabled = params.enabled
            disclaimer.save(flush: true, failOnError: true)
            result = [message: "success insert data"]
        }catch(e){
            print "error saving data"
            print e
            result = [message: "failed save data disclaimer"]
        }

        return result
    }

    def updateData(params) {
        try{
            def disclaimer = Disclaimer.get(params.id)
            print disclaimer
            disclaimer.dislcaimerTag = params.dislcaimerTag
            disclaimer.disclaimerMessage = params.disclaimerMessage
            disclaimer.module = params.module
            disclaimer.enabled = params.enabled
            disclaimer.save(flush: true, failOnError: true)
            result = [message: "success update data"]
        }catch(e){
            print "error updating data"
            print e
            result = [message: "failed update data disclaimer"]
        }

        return result
    }

    def deleteData(params) {
        try{
            def disclaimer = Disclaimer.get(params.id)
            disclaimer.delete()
            result = [message: "success delete"]
        }catch(e){
            print "error deleting data"
            print e
            result = [message: "failed delete data disclaimer"]
        }

        return result
    }
}
