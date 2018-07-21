package apifutsal

import grails.transaction.Transactional


@Transactional
class TeamService {

    def result 
    def lastUpdate = new Date()
    def filePath
    def grailsApplication 
    ImageEncrypter imageEncrypter 
    RandomGenerator randomGenerator

    def showData(params) {
        try{
            if (params.id){
                def team = Team.get(params.id as Integer)
                result = [   
                        id: team.id,
                        userId: team.userId,    
                        address: team.address,
                        contactNo: team.contactNo,
                        countTeam: team.countTeam,
                        email: team.email,
                        facebook: team.facebook,
                        idCard: team.idCard,
                        base64Image: imageEncrypter.getBase64File(grailsApplication.config.properties.profileTeamPath+"\\${team.imageProfile}"),
                        imageProfile: team.imageProfile,
                        instagram: team.instagram,
                        isReadyToMatch: team.isReadyToMatch,
                        lastUpdate: team.lastUpdate,
                        teamName: team.teamName,
                        twitter: team.twitter    ]
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
            println lastUpdate
            println randomGenerator.generator( (('A'..'Z')).join(), 6)
            executeData(team, params)
            result = [message: "success insert data"]
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
            team.userId = params.userId
            team.idCard = params.idCard
            team.teamName = params.teamName
            team.countTeam = params.countTeam
            team.contactNo = params.contactNo
            team.imageProfile = params.imageProfile
            team.address = params.address
            team.email = params.email
            team.facebook = params.facebook
            team.instagram = params.instagram
            team.twitter = params.twitter
            team.isReadyToMatch = params.isReadyToMatch
            filePath = grailsApplication.config.properties.profileTeamPath+"\\${params.imageProfile}"
            if(params.base64Image && params.base64Image!=""){
                imageEncrypter.saveBase64ToFile(params.base64Image, filePath)
            }
            team.lastUpdate = lastUpdate
            team.save(flush: true, failOnError: true)
    }

}
