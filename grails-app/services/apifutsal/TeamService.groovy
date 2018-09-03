package apifutsal

import grails.transaction.Transactional


@Transactional
class TeamService {

    def result 
    def lastUpdate = new Date()
    def filePath
    def grailsApplication 
    def team
    ImageEncrypter imageEncrypter 
    RandomGenerator randomGenerator
    SendemailService sendemailService
    ErrorHandler errorHandler

    def showData(params) {
              
        try{
            if (params.userId || params.teamId){
                team = params.userId ? Team.findByUserId(params.userId as Integer) : Team.get(params.teamId as Integer)
                result = [
                    data :[
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
                        twitter: team.twitter    
                    ],
                    message : "success get data",
                    isSuccessFull : true                  
                ]
            }
        }catch(e){
            result = errorHandler.errorChecking(null, "ERROR_GET_DATA", "Failed get Data Team", e, "team")
        }

        return result

    }


    def saveData(params) {

        try{
            team = new Team()
            saveToDB(team, params)
            def emailData = [
                to: params.email,
                subject: "Register Success",
                body: "Terima Kasih telah register di futsalo",
                userId: params.userId
            ]
            sendemailService.sendEmailAsync(emailData)
            result = [message: "success insert data", isSuccessFull : true]
        }catch(e){
            result = errorHandler.errorChecking(team, "ERROR_SAVE_DATA", "Error save data", e, "team")
        }

       return result

    }

    def updateData(params) {
        
        try{
            team = Team.get(params.id)
            saveToDB(team,params)
            result = [message: "success update data", isSuccessFull : true]
        }catch(e){
            result = errorHandler.errorChecking(team, "ERROR_UPDATE_DATA", "Failed update data team", e, "team")
        }

        return result
    }

    def deleteData(params) {

        try{
            team = Team.get(params.id)
            team.delete()
            result = [message: "success delete", isSuccessFull : true]
        }catch(e){
            result = errorHandler.errorChecking(null, "ERROR_DELETE_DATA", "Failed delete data team", e, "team")
        }

        return result

    }

    def saveToDB(team,params){

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
            team.validate()
            team.save(flush: true, failOnError: true)

    }

}
