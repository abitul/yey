package apifutsal

import grails.transaction.Transactional

@Transactional
class SosmedService {

    def result 
    def lastUpdate = new Date()

    def showData(params) {
        try{
            print lastUpdate
            Integer offset = (params.int("page")-1) * params.int("max")
            result = params.searchValue == "" ? Sosmed.listOrderByLastUpdate(order: "desc") : Sosmed.findAllBySosmedNameIlike("%${params.searchValue}%",[max: params.int("max"), sort: "sosmedName", order: "desc", offset: offset])
        }catch(e){
            print "error gettting data"
            print e
            result = [message: "failed get data sosmed"]
        }

        return result
    }

    def saveData(params) {
        try{
            def sosmed = new Sosmed()
            print lastUpdate
            sosmed.sosmedName = params.sosmedName
            sosmed.typeSosmed = params.typeSosmed
            sosmed.lastUpdate = lastUpdate
            sosmed.save(flush: true, failOnError: true)
            result = [message: "success insert data"]
        }catch(e){
            print "error saving data"
            print e
            result = [message: "failed save data sosmed"]
        }

        return result
    }

    def updateData(params) {
        try{
            def sosmed = Sosmed.get(params.id)
            print sosmed
            sosmed.sosmedName = params.sosmedName
            sosmed.typeSosmed = params.typeSosmed
            sosmed.lastUpdate = lastUpdate
            sosmed.save(flush: true, failOnError: true)
            result = [message: "success update data"]
        }catch(e){
            print "error updating data"
            print e
            result = [message: "failed update data sosmed"]
        }

        return result
    }

    def deleteData(params) {
        try{
            def sosmed = Sosmed.get(params.id)
            sosmed.delete()
            result = [message: "success delete"]
        }catch(e){
            print "error deleting data"
            print e
            result = [message: "failed delete data sosmed"]
        }

        return result
    }
}
