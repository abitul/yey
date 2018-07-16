package apifutsal

import grails.transaction.Transactional


@Transactional
class TeamService {

    def result 
    def lastUpdate = new Date()
    def filePath
    def grailsApplication 
    ImageEncrypter imageEncrypter 

    def showData(params) {
        try{
            println lastUpdate
            Integer offset = (params.int("page")-1) * params.int("max")
            def listData = params.searchValue == "" ? Team.listOrderByLastUpdate(order: "desc") : Team.findAllByTeamNameIlike("%${params.searchValue}%",[max: params.int("max"), sort: "teamName", order: "desc", offset: offset])
            result = []
            listData.each{res->
                filePath = grailsApplication.config.properties.imageUrl+"${res.imageProfile}"
                def objectData = [
                    id : res.id,
                    teamName : res.teamName,
                    countTeam : res.countTeam,
                    contactNo : res.contactNo,
                    imageName : res.imageName,
                    base64Image : imageEncrypter.getBase64File(filePath),
                    lastUpdate : res.lastUpdate
                ]
                result.push(objectData)
            }

        }catch(e){
            print "error gettting data"
            print e
            result = [message: "failed get data team"]
        }

        return result
    }


    def saveData(params) {
        try{
            def team = new Team()
            print lastUpdate
            executeData(team,params)
            result = [message: "success insert data", test: lastUpdate.toTimestamp(), dad: lastUpdate ]
        }catch(e){
            print "error saving data"
            print e
            result = [message: "failed save data team ${e}"]
        }

        return result
    }

    def updateData(params) {
        try{
            def team = Team.get(params.id)
            print team
            executeData(team,params)
            result = [message: "success update data"]
        }catch(e){
            print "error updating data"
            print e
            result = [message: "failed update data team"]
        }

        return result
    }

    def deleteData(params) {
        try{
            def team = Team.get(params.id)
            team.delete()
            result = [message: "success delete"]
        }catch(e){
            print "error deleting data"
            print e
            result = [message: "failed delete data team"]
        }

        return result
    }

    def executeData(team,params){
            team.idCard = params.idCard
            team.teamName = params.teamName
            team.countTeam = params.countTeam
            team.contactNo = params.contactNo
            team.imageProfile = params.teamName+lastUpdate.toTimestamp().getTime()+".png"
            team.address = params.address
            team.email = params.email
            team.facebook = params.facebook
            team.instagram = params.instagram
            team.twitter = params.twitter
            team.isReadyToMatch = params.isReadyToMatch
            filePath = grailsApplication.config.properties.imageUrl+"${team.imageProfile}"
            imageEncrypter.saveBase64ToFile(params.base64Image, filePath)
            team.lastUpdate = lastUpdate
            team.save(flush: true, failOnError: true)
    }

}
