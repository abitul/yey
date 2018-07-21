package apifutsal

import grails.transaction.Transactional

@Transactional
class ReviewFutsalFieldService {

    def result 
    def lastUpdate = new Date()

    def showData(params) {
        try{
            print lastUpdate
            if(params.futsalFieldId){
                def futsalField = FutsalField.get(params.futsalFieldId as Integer)
                result = ReviewFutsalField.findAllByFutsalField(futsalField)
            }else{
                Integer offset = (params.int("page")-1) * params.int("max")
                result = params.searchValue == "" ? ReviewFutsalField.listOrderByLastUpdate(order: "desc") : ReviewFutsalField.findAllByTitleIlike("%${params.searchValue}%",[max: params.int("max"), sort: "star", order: "desc", offset: offset])
            }
            
        }catch(e){
            print "error gettting data"
            print e
            result = [message: "failed get data reviewFutsalField"]
        }

        return result
    } 

    def saveData(params) {
        try{
            def reviewFutsalField = new ReviewFutsalField()
            print lastUpdate
            reviewFutsalField.title = params.title
            reviewFutsalField.comment = params.comment
            reviewFutsalField.star = params.star
            reviewFutsalField.idReviewer = params.idReviewer
            reviewFutsalField.lastUpdate = lastUpdate
            def futsalField = FutsalField.get(params.futsalFieldId)
            futsalField.addToReviewsFutsalField(reviewFutsalField).save(flush: true, failOnError: true)
            result = [message: "success insert data"]
        }catch(e){
            print "error saving data"
            print e
            result = [message: "failed save data reviewFutsalField"]
        }

        return result
    }

    def updateData(params) {
        try{
            def reviewFutsalField = ReviewFutsalField.get(params.id)
            print reviewFutsalField
            reviewFutsalField.title = params.title
            reviewFutsalField.comment = params.comment
            reviewFutsalField.star = params.star
            reviewFutsalField.idReviewer = params.idReviewer
            reviewFutsalField.lastUpdate = lastUpdate
            reviewFutsalField.save(flush: true, failOnError: true)
            result = [message: "success update data"]
        }catch(e){
            print "error updating data"
            print e
            result = [message: "failed update data reviewFutsalField"]
        }

        return result
    }

    def deleteData(params) {
        try{
            def reviewFutsalField = ReviewFutsalField.get(params.id)
            reviewFutsalField.delete()
            result = [message: "success delete"]
        }catch(e){
            print "error deleting data"
            print e
            result = [message: "failed delete data reviewFutsalField"]
        }

        return result
    }
}
