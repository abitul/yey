package apifutsal

import grails.transaction.Transactional

@Transactional
class ImageTeamService {

    def result 
    def lastUpdate = new Date()
    def filePath
    def grailsApplication 
    def imageTeam
    ErrorHandler errorHandler
    ImageEncrypter imageEncrypter 

    def showData(params) {

        try{
            println lastUpdate
            if(params.teamId){
                def team = Team.get(params.teamId as Integer)
                def listData = ImageTeam.findAllByTeam(team)
                print listData
                imageTeam = []
                listData.each{res->
                    def objectData = [ 
                                        id : res.id,
                                        category : res.category,
                                        imageName : res.imageName,
                                        base64Image : imageEncrypter.getBase64File(grailsApplication.config.properties.imageTeamPath+"\\${res.imageName}"),
                                        teamId : res.teamId ]
                    imageTeam.push(objectData)
                }
            }else{
                Integer offset = (params.int("page")-1) * params.int("max")
                imageTeam = params.searchValue ? ImageTeam.findAllByImageNameIlike("%${params.searchValue}%",[max: params.int("max"), sort: "imageName", order: "desc", offset: offset]) : ImageTeam.listOrderByLastUpdate(order: "desc") 
            } 

            result = [
                data : imageTeam,
                message : "success get data",
                isSuccessFull : true
            ]

        }catch(e){
            result = errorHandler.errorChecking(null, "ERROR_GET_DATA", "Failed get Data imageTeam", e, "imageTeam")
        }

        return result
    }

    def saveData(params) {

        try{
            imageTeam = new ImageTeam()
            print lastUpdate
            imageTeam.imageName = params.imageName
            filePath = grailsApplication.config.properties.imageTeamPath+"\\${params.imageName}"
            if(params.base64Image && params.base64Image!=""){
                imageEncrypter.saveBase64ToFile(params.base64Image, filePath)
            }
            imageTeam.category = params.category
            imageTeam.lastUpdate = lastUpdate
            def team = Team.get(params.teamId)
            team.addToImagesTeam(image).save(flush: true, failOnError: true)
            result = [message: "success insert data", isSuccessFull : true]
        }catch(e){
            result = errorHandler.errorChecking(team, "ERROR_SAVE_DATA", "Error save data", e, "imageTeam")
        }

        return result

    }

    def updateData(params) {

        try{
            imageTeam = ImageTeam.get(params.id)
            print image
            imageTeam.imageName = params.imageName
            filePath = grailsApplication.config.properties.imageTeamPath+"\\${params.imageName}"
            if(params.base64Image && params.base64Image!=""){
                imageEncrypter.saveBase64ToFile(params.base64Image, filePath)
            }
            imageTeam.category = params.category
            imageTeam.lastUpdate = lastUpdate
            imageTeam.save(flush: true, failOnError: true)
            result = [message: "success update data", isSuccessFull : true]
        }catch(e){
            result = errorHandler.errorChecking(team, "ERROR_UPDATE_DATA", "Failed update data imageTeam", e, "imageTeam")
        }

        return result

    }

    def deleteData(params) {

        try{
            imageTeam = ImageTeam.get(params.id)
            imageTeam.delete()
            result = [message: "success delete", isSuccessFull : true]
        }catch(e){
            result = errorHandler.errorChecking(null, "ERROR_DELETE_DATA", "Failed delete data imageTeam", e, "imageTeam")
        }

        return result
        
    }
}
