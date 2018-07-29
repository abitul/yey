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
            if (params.id){
                team = Team.get(params.id as Integer)
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
                    isSuccessFull : true,                    
                ]
            }
        }catch(e){
            errorChecking(null, "ERROR_GET_DATA", "Failed get Data Team", e)
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
            sendemailService.sendDirectEmail(emailData)
            result = [message: "success insert data", isSuccessFull : true]
        }catch(e){
            errorChecking(team, "ERROR_SAVE_DATA", "Error save data", e)
        }

       return result

    }

    def updateData(params) {
        
        try{
            team = Team.get(params.id)
            saveToDB(team,params)
            result = [message: "success update data", isSuccessFull : true]
        }catch(e){
            errorChecking(team, "ERROR_UPDATE_DATA", "Failed update data team", e)
        }

        return result
    }

    def deleteData(params) {

        try{
            team = Team.get(params.id)
            team.delete()
            result = [message: "success delete", isSuccessFull : true]
        }catch(e){
            errorChecking(null, "ERROR_UPDATE_DATA", "Failed delete data team", e)
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

    def errorChecking(team, errorTag, errorAction, e){

        errorHandler = new ErrorHandler()
        if (team) {
            if (team.errors.hasFieldErrors("idCard")) {
                errorHandler.setError("IDCARD_NOT_UNIQUE", "Please change value in idcard "+team.errors.getFieldError("idCard").rejectedValue)
            }

            if (team.errors.hasFieldErrors("email")) {
                errorHandler.setError("EMAIL_NOT_VALID", "Please change value in email "+team.errors.getFieldError("email").rejectedValue)
            }

            result = [errors: errorHandler.getListError(), isSuccessFull: false]
        }else{
            println e
            errorHandler.setError("${errorTag}", "${errorAction} ${e}")
            result = [errors: errorHandler.getListError(), isSuccessFull: false]
        }
        
    }

}
