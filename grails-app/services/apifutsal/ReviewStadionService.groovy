package apifutsal

import grails.transaction.Transactional

@Transactional
class ReviewStadionService {

    def result 
    def lastUpdate = new Date()
    def reviewStadion
    def showData(params) {

        try{
            print lastUpdate
            if(params.stadionId){
                def stadion = Stadion.get(params.stadionId as Integer)
                reviewStadion = ReviewStadion.findAllByStadion(stadion)
            }else{
                Integer offset = (params.int("page")-1) * params.int("max")
                reviewStadion = params.searchValue  ? ReviewStadion.findAllByTitleIlike("%${params.searchValue}%",[max: params.int("max"), sort: "star", order: "desc", offset: offset]) : ReviewStadion.listOrderByLastUpdate(order: "desc") 
            }


            result = [
                data : reviewStadion,
                message : "success get data",
                isSuccessFull : true
            ]
            
        }catch(e){
            result = errorHandler.errorChecking(null, "ERROR_GET_DATA", "Failed get Data reviewStadion", e, "reviewStadion")
        }

        return result

    } 

    def saveData(params) {

        try{
            reviewStadion = new ReviewStadion()
            print lastUpdate
            reviewStadion.title = params.title
            reviewStadion.comment = params.comment
            reviewStadion.star = params.star
            reviewStadion.idReviewer = params.idReviewer
            reviewStadion.lastUpdate = lastUpdate
            def stadion = Stadion.get(params.stadionId)
            stadion.addToReviewsStadion(reviewStadion).save(flush: true, failOnError: true)
            result = [message: "success insert data", isSuccessFull : true]
        }catch(e){
            result = errorHandler.errorChecking(team, "ERROR_SAVE_DATA", "Error save data", e, "reviewStadion")
        }

        return result

    }

    def updateData(params) {

        try{
            reviewStadion = ReviewStadion.get(params.id)
            print reviewStadion
            reviewStadion.title = params.title
            reviewStadion.comment = params.comment
            reviewStadion.star = params.star
            reviewStadion.idReviewer = params.idReviewer
            reviewStadion.lastUpdate = lastUpdate
            reviewStadion.save(flush: true, failOnError: true)
            result = [message: "success update data", isSuccessFull : true]
        }catch(e){
            result = errorHandler.errorChecking(team, "ERROR_UPDATE_DATA", "Failed update data reviewStadion", e, "reviewStadion")
        }

        return result

    }

    def deleteData(params) {

        try{
            reviewStadion = ReviewStadion.get(params.id)
            reviewStadion.delete()
            result = [message: "success delete", isSuccessFull : true]
        }catch(e){
            result = errorHandler.errorChecking(null, "ERROR_DELETE_DATA", "Failed delete data reviewStadion", e, "reviewStadion")
        }

        return result
        
    }
}
