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
            result = params.searchValue == "" ? Review.listOrderByLastUpdate(order: "desc") : Review.findAllByTitleIlike("%${params.searchValue}%",[max: params.int("max"), sort: "star", order: "desc", offset: offset])
        }catch(e){
            print "error gettting data"
            print e
            result = [message: "failed get data review"]
        }

        return result
    } 

    def saveData(params) {
        try{
            def review = new Review()
            print lastUpdate
            review.title = params.title
            review.comment = params.comment
            review.star = params.star
            review.idReviewed = params.idReviewed
            review.idReviewer = params.idReviewer
            review.lastUpdate = lastUpdate
            review.save(flush: true, failOnError: true)
            result = [message: "success insert data"]
        }catch(e){
            print "error saving data"
            print e
            result = [message: "failed save data review"]
        }

        return result
    }

    def updateData(params) {
        try{
            def review = Review.get(params.id)
            print review
            review.title = params.title
            review.comment = params.comment
            review.star = params.star
            review.idReviewed = params.idReviewed
            review.idReviewer = params.idReviewer
            review.lastUpdate = lastUpdate
            review.save(flush: true, failOnError: true)
            result = [message: "success update data"]
        }catch(e){
            print "error updating data"
            print e
            result = [message: "failed update data review"]
        }

        return result
    }

    def deleteData(params) {
        try{
            def review = Review.get(params.id)
            review.delete()
            result = [message: "success delete"]
        }catch(e){
            print "error deleting data"
            print e
            result = [message: "failed delete data review"]
        }

        return result
    }
}
