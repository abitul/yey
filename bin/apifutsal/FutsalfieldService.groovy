package apifutsal

import grails.transaction.Transactional

@Transactional
class FutsalFieldService {

    def result 
    def lastUpdate = new Date()

    def showData(params) {
        try{
            print lastUpdate
            Integer offset = (params.int("page")-1) * params.int("max")
            result = params.searchValue == "" ? FutsalField.listOrderByLastUpdate(order: "desc") : FutsalField.findAllByFutsalFieldNameIlike("%${params.searchValue}%",[max: params.int("max"), sort: "futsalFieldName", order: "desc", offset: offset])
        }catch(e){
            print "error gettting data"
            print e
            result = [message: "failed get data futsalField"]
        }

        return result
    }

    def saveData(params) {
        try{
            def futsalField = new FutsalField()
            print lastUpdate
            futsalField.futsalFieldName = params.futsalFieldName
            futsalField.typefutsalField = params.typefutsalField
            futsalField.lastUpdate = lastUpdate
            futsalField.save(flush: true, failOnError: true)
            result = [message: "success insert data"]
        }catch(e){
            print "error saving data"
            print e
            result = [message: "failed save data futsalField"]
        }

        return result
    }

    def updateData(params) {
        try{
            def futsalField = FutsalField.get(params.id)
            print futsalField
            futsalField.futsalFieldName = params.futsalFieldName
            futsalField.typefutsalField = params.typefutsalField
            futsalField.lastUpdate = lastUpdate
            futsalField.save(flush: true, failOnError: true)
            result = [message: "success update data"]
        }catch(e){
            print "error updating data"
            print e
            result = [message: "failed update data futsalField"]
        }

        return result
    }

    def deleteData(params) {
        try{
            def futsalField = FutsalField.get(params.id)
            futsalField.delete()
            result = [message: "success delete"]
        }catch(e){
            print "error deleting data"
            print e
            result = [message: "failed delete data futsalField"]
        }

        return result
    }
}

