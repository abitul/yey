package apifutsal

import grails.transaction.Transactional

@Transactional
class ReviewFutsalFieldService {

    def result 
    def lastUpdate = new Date()
    def reviewFutsalField

    def showData(params) {

        try{
            print lastUpdate
            if(params.futsalFieldId){
                def futsalField = FutsalField.get(params.futsalFieldId as Integer)
                reviewFutsalField = ReviewFutsalField.findAllByFutsalField(futsalField)
            }else{
                Integer offset = (params.int("page")-1) * params.int("max")
                reviewFutsalField = params.searchValue  ? ReviewFutsalField.findAllByTitleIlike("%${params.searchValue}%",[max: params.int("max"), sort: "star", order: "desc", offset: offset]) : ReviewFutsalField.listOrderByLastUpdate(order: "desc")
            }
            

            result = [
                data : reviewFutsalField,
                message : "success get data",
                isSuccessFull : true
            ]

        }catch(e){
            result = errorHandler.errorChecking(null, "ERROR_GET_DATA", "Failed get Data reviewFutsalField", e, "reviewFutsalField")
        }

        return result

    } 

    def saveData(params) {

        try{
            reviewFutsalField = new ReviewFutsalField()
            print lastUpdate
            reviewFutsalField.title = params.title
            reviewFutsalField.comment = params.comment
            reviewFutsalField.star = params.star
            reviewFutsalField.idReviewer = params.idReviewer
            reviewFutsalField.lastUpdate = lastUpdate
            def futsalField = FutsalField.get(params.futsalFieldId)
            futsalField.addToReviewsFutsalField(reviewFutsalField).save(flush: true, failOnError: true)
            result = [message: "success insert data", isSuccessFull : true]
        }catch(e){
            result = errorHandler.errorChecking(team, "ERROR_SAVE_DATA", "Error save data", e, "reviewFutsalField")
        }

        return result

    }

    def updateData(params) {

        try{
            reviewFutsalField = ReviewFutsalField.get(params.id)
            print reviewFutsalField
            reviewFutsalField.title = params.title
            reviewFutsalField.comment = params.comment
            reviewFutsalField.star = params.star
            reviewFutsalField.idReviewer = params.idReviewer
            reviewFutsalField.lastUpdate = lastUpdate
            reviewFutsalField.save(flush: true, failOnError: true)
            result = [message: "success update data", isSuccessFull : true]
        }catch(e){
            result = errorHandler.errorChecking(team, "ERROR_UPDATE_DATA", "Failed update data reviewFutsalField", e, "reviewFutsalField")
        }

        return result

    }

    def deleteData(params) {

        try{
            reviewFutsalField = ReviewFutsalField.get(params.id)
            reviewFutsalField.delete()
            result = [message: "success delete", isSuccessFull : true]
        }catch(e){
            result = errorHandler.errorChecking(null, "ERROR_DELETE_DATA", "Failed delete data reviewFutsalField", e, "reviewFutsalField")
        }

        return result
        
    }
}
