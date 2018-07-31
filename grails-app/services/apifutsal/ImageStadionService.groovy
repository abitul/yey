package apifutsal

import grails.transaction.Transactional

@Transactional
class ImageStadionService {

    def result 
    def lastUpdate = new Date()
    def filePath
    def grailsApplication 
    def imageStadion
    ImageEncrypter imageEncrypter 
    ErrorHandler errorHandler

    def showData(params) {
        try{
            println lastUpdate
            if(params.stadionId){
                def stadion = Stadion.get(params.stadionId as Integer)
                def listData = ImageStadion.findAllByStadion(stadion)
                print listData
                imageStadion = []
                listData.each{res->
                    def objectData = [ 
                                        id : res.id,
                                        category : res.category,
                                        imageName : res.imageName,
                                        base64Image : imageEncrypter.getBase64File(grailsApplication.config.properties.imageStadionPath+"\\${res.imageName}"),
                                        stadionId : res.stadionId ]
                    imageStadion.push(objectData)
                }
            }else{
                Integer offset = (params.int("page")-1) * params.int("max")
                imageStadion = params.searchValue ? ImageStadion.findAllByImageNameIlike("%${params.searchValue}%",[max: params.int("max"), sort: "imageName", order: "desc", offset: offset]) : ImageStadion.listOrderByLastUpdate(order: "desc") 
            } 

            result = [
                data : imageStadion,
                message : "success get data",
                isSuccessFull : true
            ]

        }catch(e){
            result = errorHandler.errorChecking(null, "ERROR_GET_DATA", "Failed get Data imageStadion", e, "imageStadion")
        }

        return result
    }

    def saveData(params) {

        try{
            imageStadion = new ImageStadion()
            print lastUpdate
            imageStadion.imageName = params.imageName
            filePath = grailsApplication.config.properties.imageStadionPath+"\\${params.imageName}"
            if(params.base64Image && params.base64Image!=""){
                imageEncrypter.saveBase64ToFile(params.base64Image, filePath)
            }
            imageStadion.category = params.category
            imageStadion.lastUpdate = lastUpdate
            def stadion = Stadion.get(params.stadionId)
            stadion.addToImagesStadion(image).save(flush: true, failOnError: true)
            result = [message: "success insert data", isSuccessFull : true]
        }catch(e){
            result = errorHandler.errorChecking(team, "ERROR_SAVE_DATA", "Error save data", e, "imageStadion")
        }

        return result

    }

    def updateData(params) {

        try{
            imageStadion = ImageStadion.get(params.id)
            print image
            imageStadion.imageName = params.imageName
            filePath = grailsApplication.config.properties.imageStadionPath+"\\${params.imageName}"
            if(params.base64Image && params.base64Image!=""){
                imageEncrypter.saveBase64ToFile(params.base64Image, filePath)
            }
            imageStadion.category = params.category
            imageStadion.lastUpdate = lastUpdate
            imageStadion.save(flush: true, failOnError: true)
            result = [message: "success update data", isSuccessFull : true]
        }catch(e){
            result = errorHandler.errorChecking(team, "ERROR_UPDATE_DATA", "Failed update data imageStadion", e, "imageStadion")
        }

        return result

    }

    def deleteData(params) {

        try{
            imageStadion = ImageStadion.get(params.id)
            imageStadion.delete()
            result = [message: "success delete", isSuccessFull : true]
        }catch(e){
            result = errorHandler.errorChecking(null, "ERROR_DELETE_DATA", "Failed delete data imageStadion", e, "imageStadion")
        }

        return result
        
    }
}
