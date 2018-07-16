package apifutsal

import grails.transaction.Transactional

@Transactional
class ReviewStadionService {

    def result 
    def lastUpdate = new Date()

    def showData(params) {
        try{
            print lastUpdate
            Integer offset = (params.int("page")-1) * params.int("max")
            result = params.searchValue == "" ? ReviewStadion.listOrderByLastUpdate(order: "desc") : ReviewStadion.findAllByTitleIlike("%${params.searchValue}%",[max: params.int("max"), sort: "star", order: "desc", offset: offset])
        }catch(e){
            print "error gettting data"
            print e
            result = [message: "failed get data reviewStadion"]
        }

        return result
    } 

    def saveData(params) {
        try{
            def reviewStadion = new ReviewStadion()
            print lastUpdate
            reviewStadion.title = params.title
            reviewStadion.comment = params.comment
            reviewStadion.star = params.star
            reviewStadion.idReviewer = params.idReviewer
            reviewStadion.lastUpdate = lastUpdate
            reviewStadion.save(flush: true, failOnError: true)
            result = [message: "success insert data"]
        }catch(e){
            print "error saving data"
            print e
            result = [message: "failed save data reviewStadion"]
        }

        return result
    }

    def updateData(params) {
        try{
            def reviewStadion = ReviewStadion.get(params.id)
            print reviewStadion
            reviewStadion.title = params.title
            reviewStadion.comment = params.comment
            reviewStadion.star = params.star
            reviewStadion.idReviewer = params.idReviewer
            reviewStadion.lastUpdate = lastUpdate
            reviewStadion.save(flush: true, failOnError: true)
            result = [message: "success update data"]
        }catch(e){
            print "error updating data"
            print e
            result = [message: "failed update data reviewStadion"]
        }

        return result
    }

    def deleteData(params) {
        try{
            def reviewStadion = ReviewStadion.get(params.id)
            reviewStadion.delete()
            result = [message: "success delete"]
        }catch(e){
            print "error deleting data"
            print e
            result = [message: "failed delete data reviewStadion"]
        }

        return result
    }
}
