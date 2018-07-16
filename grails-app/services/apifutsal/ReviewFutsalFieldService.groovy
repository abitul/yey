package apifutsal

import grails.transaction.Transactional

@Transactional
class ReviewFutsalFieldService {

    // def result 
    // def lastUpdate = new Date()

    // def showData(params) {
    //     try{
    //         print lastUpdate
    //         Integer offset = (params.int("page")-1) * params.int("max")
    //         result = params.searchValue == "" ? ReviewFutsal.listOrderByLastUpdate(order: "desc") : ReviewFutsal.findAllByTitleIlike("%${params.searchValue}%",[max: params.int("max"), sort: "star", order: "desc", offset: offset])
    //     }catch(e){
    //         print "error gettting data"
    //         print e
    //         result = [message: "failed get data reviewFutsal"]
    //     }

    //     return result
    // } 

    // def saveData(params) {
    //     try{
    //         def reviewFutsal = new ReviewFutsal()
    //         print lastUpdate
    //         reviewFutsal.title = params.title
    //         reviewFutsal.comment = params.comment
    //         reviewFutsal.star = params.star
    //         reviewFutsal.idReviewer = params.idReviewer
    //         reviewFutsal.lastUpdate = lastUpdate
    //         reviewFutsal.save(flush: true, failOnError: true)
    //         result = [message: "success insert data"]
    //     }catch(e){
    //         print "error saving data"
    //         print e
    //         result = [message: "failed save data reviewFutsal"]
    //     }

    //     return result
    // }

    // def updateData(params) {
    //     try{
    //         def reviewFutsal = ReviewFutsal.get(params.id)
    //         print reviewFutsal
    //         reviewFutsal.title = params.title
    //         reviewFutsal.comment = params.comment
    //         reviewFutsal.star = params.star
    //         reviewFutsal.idReviewer = params.idReviewer
    //         reviewFutsal.lastUpdate = lastUpdate
    //         reviewFutsal.save(flush: true, failOnError: true)
    //         result = [message: "success update data"]
    //     }catch(e){
    //         print "error updating data"
    //         print e
    //         result = [message: "failed update data reviewFutsal"]
    //     }

    //     return result
    // }

    // def deleteData(params) {
    //     try{
    //         def reviewFutsal = ReviewFutsal.get(params.id)
    //         reviewFutsal.delete()
    //         result = [message: "success delete"]
    //     }catch(e){
    //         print "error deleting data"
    //         print e
    //         result = [message: "failed delete data reviewFutsal"]
    //     }

    //     return result
    // }
}
