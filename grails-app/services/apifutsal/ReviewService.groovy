package apifutsal

import grails.transaction.Transactional

@Transactional
class ReviewService {

    def result 
    def lastUpdate = new Date()

    def showData(params) {
        try{
            print lastUpdate
            Integer offset = (params.int("page")-1) * params.int("max")
            result = params.searchValue == "" ? Futsalfield.listOrderByLastUpdate(order: "desc") : Futsalfield.findAllByFutsalfieldNameIlike("%"+params.searchValue+"%",[max: params.int("max"), sort: "futsalfieldName", order: "desc", offset: offset])
        }catch(e){
            print "error gettting data"
            print e
            result = [message: "failed get data futsalfield"]
        }

        return result
    }

    def saveData(params) {
        try{
            def futsalfield = new Futsalfield()
            print lastUpdate
            futsalfield.futsalfieldName = params.futsalfieldName
            futsalfield.typeFutsalfield = params.typeFutsalfield
            futsalfield.lastUpdate = lastUpdate
            futsalfield.save(flush: true, failOnError: true)
            result = [message: "success insert data"]
        }catch(e){
            print "error saving data"
            print e
            result = [message: "failed save data futsalfield"]
        }

        return result
    }

    def updateData(params) {
        try{
            def futsalfield = Futsalfield.get(params.id)
            print futsalfield
            futsalfield.futsalfieldName = params.futsalfieldName
            futsalfield.typeFutsalfield = params.typeFutsalfield
            futsalfield.lastUpdate = lastUpdate
            futsalfield.save(flush: true, failOnError: true)
            result = [message: "success update data"]
        }catch(e){
            print "error updating data"
            print e
            result = [message: "failed update data futsalfield"]
        }

        return result
    }

    def deleteData(params) {
        try{
            def futsalfield = Futsalfield.get(params.id)
            futsalfield.delete()
            result = [message: "success delete"]
        }catch(e){
            print "error deleting data"
            print e
            result = [message: "failed delete data futsalfield"]
        }

        return result
    }
}
