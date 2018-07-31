package apifutsal

import grails.transaction.Transactional

@Transactional
class ReviewTeamService {
    
    def result 
    def lastUpdate = new Date()
    def reviewTeam
    ErrorHandler errorHandler

    def showData(params) {

        try{
            print lastUpdate
            if(params.teamId){
                def team = Team.get(params.teamId as Integer)
                reviewTeam = ReviewTeam.findAllByTeam(team)
            }else{
                Integer offset = (params.int("page")-1) * params.int("max")
                reviewTeam = params.searchValue  ? ReviewTeam.findAllByTitleIlike("%${params.searchValue}%",[max: params.int("max"), sort: "star", order: "desc", offset: offset]) : ReviewTeam.listOrderByLastUpdate(order: "desc")
            }

            result = [
                data : reviewTeam,
                message : "success get data",
                isSuccessFull : true
            ]

        }catch(e){
            result = errorHandler.errorChecking(null, "ERROR_GET_DATA", "Failed get Data reviewTeam", e, "reviewTeam")
        }

        return result

    } 

    def saveData(params) {

        try{
            reviewTeam = new ReviewTeam()
            print lastUpdate
            reviewTeam.title = params.title
            reviewTeam.comment = params.comment
            reviewTeam.star = params.star
            reviewTeam.idReviewer = params.idReviewer
            reviewTeam.lastUpdate = lastUpdate
            def team = Team.get(params.teamId)
            team.addToReviewsTeam(reviewTeam).save(flush: true, failOnError: true)
            result = [message: "success insert data", isSuccessFull : true]
        }catch(e){
            result = errorHandler.errorChecking(team, "ERROR_SAVE_DATA", "Error save data", e, "reviewTeam")
        }

        return result

    }

    def updateData(params) {

        try{
            reviewTeam = ReviewTeam.get(params.id)
            print reviewTeam
            reviewTeam.title = params.title
            reviewTeam.comment = params.comment
            reviewTeam.star = params.star
            reviewTeam.idReviewer = params.idReviewer
            reviewTeam.lastUpdate = lastUpdate
            reviewTeam.save(flush: true, failOnError: true)
            result = [message: "success update data", isSuccessFull : true]
        }catch(e){
            result = errorHandler.errorChecking(team, "ERROR_UPDATE_DATA", "Failed update data reviewTeam", e, "reviewTeam")
        }

        return result

    }

    def deleteData(params) {

        try{
            reviewTeam = ReviewTeam.get(params.id)
            reviewTeam.delete()
            result = [message: "success delete", isSuccessFull : true]
        }catch(e){
            result = errorHandler.errorChecking(null, "ERROR_DELETE_DATA", "Failed delete data reviewTeam", e, "reviewTeam")
        }

        return result
        
    }
}
