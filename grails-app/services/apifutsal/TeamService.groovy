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
            println lastUpdate
            if (params.id){
                Team.get(params.int("id")).each{res->
                   result = [   id: res.id,    
                        address: res.address,
                        contactNo: res.contactNo,
                        countTeam: res.countTeam,
                        email: res.email,
                        facebook: res.facebook,
                        idCard: res.idCard,
                        imageProfile: imageEncrypter.getBase64File(grailsApplication.config.properties.imageUrl+"${res.imageProfile}"),
                        instagram: res.instagram,
                        isReadyToMatch: res.isReadyToMatch,
                        lastUpdate: res.lastUpdate,
                        teamName: res.teamName,
                        twitter: res.twitter    ]
                }
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
            executeData(team,params)
            def getTeam = team.findByIdCard("${params.idCard}")
            println getTeam
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
            team.imageProfile = "${params.idCard}.png"
            team.address = params.address
            team.email = params.email
            team.facebook = params.facebook
            team.instagram = params.instagram
            team.twitter = params.twitter
            team.isReadyToMatch = params.isReadyToMatch
            filePath = grailsApplication.config.properties.imageUrl+"${team.imageProfile}"
            if(params.base64Image && params.base64Image!=""){
                imageEncrypter.saveBase64ToFile(params.base64Image, filePath)
            }
            team.lastUpdate = lastUpdate
            team.save(flush: true, failOnError: true)
    }

}
