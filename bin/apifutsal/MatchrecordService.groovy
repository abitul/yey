package apifutsal

import grails.transaction.Transactional

@Transactional
class MatchRecordService {

    def result 
    def createdDate = new Date()

    def showData(params) {
        try{
            print createdDate
            if(params.teamId){
                def team = Team.get(params.teamId as Integer)
                result = MatchRecord.findAllByTeam(team)
            }else{
                Integer offset = (params.int("page")-1) * params.int("max")
                result = params.searchValue == "" ? MatchRecord.listOrderByCreatedDate(order: "desc") : MatchRecord.findAllByStatusIlike("%${params.searchValue}%",[max: params.int("max"), sort: "status", order: "desc", offset: offset])
            }
        }catch(e){
            print "error gettting data"
            print e
            result = [message: "failed get data matchRecord"]
        }

        return result
    }

    def saveData(params) {
        try{
            // def statusTeam1
            // def statusTeam2
            // if(params.scoreTeam1 > params.scoreTeam2){
            //     statusTeam1 = "WIN"
            //     statusTeam2 = "LOST"
            // }else if(params.scoreTeam1 < params.scoreTeam2){
            //     statusTeam1 = "LOST"
            //     statusTeam2 = "WIN"
            // }else if(params.scoreTeam1 == params.scoreTeam2){
            //     statusTeam1 = "LOST"
            //     statusTeam2 = "WIN"
            // }

            // def matchRecordTeam1 = new MatchRecord()
            // print createdDate
            // matchRecordTeam1.status = statusTeam1
            // matchRecordTeam1.score = params.score
            // matchRecordTeam1.createdDate = createdDate
            // def team1 = Team.get(params.team1Id)
            // team1.addToMatchesRecords(matchRecordTeam1).save(flush: true, failOnError: true)

            // def matchRecordTeam2 = new MatchRecord()
            // print createdDate
            // matchRecordTeam2.status = statusTeam2
            // matchRecordTeam2.score = params.score
            // matchRecordTeam2.createdDate = createdDate
            
            // def team2 = Team.get(params.team2Id)
            // team2.addToMatchesRecords(matchRecordTeam2).save(flush: true, failOnError: true)


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
                def matchRecord = new MatchRecord()
                print createdDate
                matchRecord.status = params[i].status
                matchRecord.score = params[i].score
                matchRecord.versusTeamId = i == 0 ? params[1].teamId : params[0].teamId
                matchRecord.versusTeamScore = i == 0 ? params[1].score : params[0].score
                matchRecord.createdDate = createdDate
                def team = Team.get(params[i].teamId)
                team.addToMatchRecords(matchRecord).save(flush: true, failOnError: true)
            }
            
            result = [message: "success insert data"]

        }catch(e){
            print "error saving data"
            print e
            result = [message: "failed save data matchRecord"]
        }

        return result
    }

    def updateData(params) {
        try{
            def matchRecord = MatchRecord.get(params.id)
            print matchRecord
            matchRecord.status = params.status
            matchRecord.createdDate = params.createdDate
            matchRecord.save(flush: true, failOnError: true)
            result = [message: "success update data"]
        }catch(e){
            print "error updating data"
            print e
            result = [message: "failed update data matchRecord"]
        }

        return result
    }

    def deleteData(params) {
        try{
            def matchRecord = MatchRecord.get(params.id)
            matchRecord.delete()
            result = [message: "success delete"]
        }catch(e){
            print "error deleting data"
            print e
            result = [message: "failed delete data matchRecord"]
        }

        return result
    }
}
