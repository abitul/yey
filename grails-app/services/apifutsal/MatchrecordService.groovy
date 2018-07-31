package apifutsal

import grails.transaction.Transactional

@Transactional
class MatchRecordService {

    def result 
    def createdDate = new Date()
    def matchRecord
    
    def showData(params) {

        try{
            print createdDate
            if(params.teamId){
                def team = Team.get(params.teamId as Integer)
                matchRecord = MatchRecord.findAllByTeam(team)
            }else{
                Integer offset = (params.int("page")-1) * params.int("max")
                matchRecord = params.searchValue  ? MatchRecord.findAllByStatusIlike("%${params.searchValue}%",[max: params.int("max"), sort: "status", order: "desc", offset: offset]) : MatchRecord.listOrderByCreatedDate(order: "desc") 
            }

            result = [
                data : matchRecord,
                message : "success get data",
                isSuccessFull : true
            ]
            
        }catch(e){
            result = errorHandler.errorChecking(null, "ERROR_GET_DATA", "Failed get Data matchRecord", e, "matchRecord")
        }

        return result

    }

    def saveData(params) {

        try{
            
            println params
            if(params[0].score > params[1].score){
                params[0].status = "WIN"
                params[1].status = "LOST"
            }else if(params[0].score < params[1].score){
                params[0].status = "LOST"
                params[1].status = "WIN"
            }else if(params[0].score == params[1].score){
                params[0].status = "DRAW"
                params[1].status = "DRAW"
            }

            for(def i= 0; i<2; i++){
                matchRecord = new MatchRecord()
                print createdDate
                matchRecord.status = params[i].status
                matchRecord.score = params[i].score
                matchRecord.versusTeamId = i == 0 ? params[1].teamId : params[0].teamId
                matchRecord.versusTeamScore = i == 0 ? params[1].score : params[0].score
                matchRecord.createdDate = createdDate
                def team = Team.get(params[i].teamId)
                team.addToMatchRecords(matchRecord).save(flush: true, failOnError: true)
            }
            
            result = [message: "success insert data", isSuccessFull : true]

        }catch(e){
            result = errorHandler.errorChecking(team, "ERROR_SAVE_DATA", "Error save data", e, "matchRecord")
        }

        return result

    }

    def updateData(params) {

        try{
            matchRecord = MatchRecord.get(params.id)
            print matchRecord
            matchRecord.status = params.status
            matchRecord.createdDate = params.createdDate
            matchRecord.save(flush: true, failOnError: true)
            result = [message: "success update data", isSuccessFull : true]
        }catch(e){
            result = errorHandler.errorChecking(team, "ERROR_UPDATE_DATA", "Failed update data matchRecord", e, "matchRecord")
        }

        return result

    }

    def deleteData(params) {

        try{
            matchRecord = MatchRecord.get(params.id)
            matchRecord.delete()
            result = [message: "success delete", isSuccessFull : true]
        }catch(e){
            result = errorHandler.errorChecking(null, "ERROR_DELETE_DATA", "Failed delete data matchRecord", e, "matchRecord")
        }

        return result
        
    }
}
