package apifutsal

import grails.transaction.Transactional

@Transactional
class ReviewTeamService {
    
    def result 
    def lastUpdate = new Date()

    def showData(params) {
        try{
            print lastUpdate
            Integer offset = (params.int("page")-1) * params.int("max")
            result = params.searchValue == "" ? ReviewTeam.listOrderByLastUpdate(order: "desc") : ReviewTeam.findAllByTitleIlike("%${params.searchValue}%",[max: params.int("max"), sort: "star", order: "desc", offset: offset])
        }catch(e){
            print "error gettting data"
            print e
            result = [message: "failed get data reviewTeam"]
        }

        return result
    } 

    def saveData(params) {
        try{
            def reviewTeam = new ReviewTeam()
            print lastUpdate
            reviewTeam.title = params.title
            reviewTeam.comment = params.comment
            reviewTeam.star = params.star
            reviewTeam.idReviewer = params.idReviewer
            reviewTeam.lastUpdate = lastUpdate
            reviewTeam.save(flush: true, failOnError: true)
            result = [message: "success insert data"]
        }catch(e){
            print "error saving data"
            print e
            result = [message: "failed save data reviewTeam"]
        }

        return result
    }

    def updateData(params) {
        try{
            def reviewTeam = ReviewTeam.get(params.id)
            print reviewTeam
            reviewTeam.title = params.title
            reviewTeam.comment = params.comment
            reviewTeam.star = params.star
            reviewTeam.idReviewer = params.idReviewer
            reviewTeam.lastUpdate = lastUpdate
            reviewTeam.save(flush: true, failOnError: true)
            result = [message: "success update data"]
        }catch(e){
            print "error updating data"
            print e
            result = [message: "failed update data reviewTeam"]
        }

        return result
    }

    def deleteData(params) {
        try{
            def reviewTeam = ReviewTeam.get(params.id)
            reviewTeam.delete()
            result = [message: "success delete"]
        }catch(e){
            print "error deleting data"
            print e
            result = [message: "failed delete data reviewTeam"]
        }

        return result
    }
}
