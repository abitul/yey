package apifutsal

import grails.transaction.Transactional

@Transactional
class ImageFutsalFieldService {

    def result 
    def lastUpdate = new Date()
    def filePath
    def grailsApplication
    def imageFutsalField
    ImageEncrypter imageEncrypter 

    def showData(params) {

        try{
            println lastUpdate
            if(params.futsalFieldId){
                def futsalField = FutsalField.get(params.futsalFieldId as Integer)
                def listData = ImageFutsalField.findAllByFutsalField(futsalField)
                print listData
                imageFutsalField = []
                listData.each{res->
                    def objectData = [ 
                                        id : res.id,
                                        category : res.category,
                                        imageName : res.imageName,
                                        base64Image : imageEncrypter.getBase64File(grailsApplication.config.properties.imageFutsalFieldPath+"\\${res.imageName}"),
                                        futsalFieldId : res.futsalFieldId ]
                    imageFutsalField.push(objectData)
                }
            }else{
                Integer offset = (params.int("page")-1) * params.int("max")
                imageFutsalField = params.searchValue ? ImageFutsalField.findAllByImageNameIlike("%${params.searchValue}%",[max: params.int("max"), sort: "imageName", order: "desc", offset: offset]) : ImageFutsalField.listOrderByLastUpdate(order: "desc") 
            } 

            result = [
                data : imageFutsalField,
                message : "success get data",
                isSuccessFull : true
            ]
            
        }catch(e){
            result = errorHandler.errorChecking(null, "ERROR_GET_DATA", "Failed get Data imageFutsalField", e, "imageFutsalField")
        }

        return result
    }

    def saveData(params) {

        try{
            imageFutsalField = new ImageFutsalField()
            print lastUpdate
            imageFutsalField.imageName = params.imageName
            filePath = grailsApplication.config.properties.imageFutsalFieldPath+"\\${params.imageName}"
            if(params.base64Image && params.base64Image!=""){
                imageEncrypter.saveBase64ToFile(params.base64Image, filePath)
            }
            imageFutsalField.category = params.category
            imageFutsalField.lastUpdate = lastUpdate
            def futsalField = FutsalField.get(params.futsalFieldId)
            futsalField.addToImagesFutsalField(image).save(flush: true, failOnError: true)
            result = [message: "success insert data", isSuccessFull : true]
        }catch(e){
            result = errorHandler.errorChecking(team, "ERROR_SAVE_DATA", "Error save data", e, "imageFutsalField")
        }

        return result

    }

    def updateData(params) {

        try{
            imageFutsalField = ImageFutsalField.get(params.id)
            print image
            imageFutsalField.imageName = params.imageName
            filePath = grailsApplication.config.properties.imageFutsalFieldPath+"\\${params.imageName}"
            if(params.base64Image && params.base64Image!=""){
                imageEncrypter.saveBase64ToFile(params.base64Image, filePath)
            }
            imageFutsalField.category = params.category
            imageFutsalField.lastUpdate = lastUpdate
            imageFutsalField.save(flush: true, failOnError: true)
            result = [message: "success update data", isSuccessFull : true]
        }catch(e){
            result = errorHandler.errorChecking(team, "ERROR_UPDATE_DATA", "Failed update data imageFutsalField", e, "imageFutsalField")
        }

        return result

    }

    def deleteData(params) {

        try{
            imageFutsalField = ImageFutsalField.get(params.id)
            imageFutsalField.delete()
            result = [message: "success delete", isSuccessFull : true]
        }catch(e){
            result = errorHandler.errorChecking(null, "ERROR_DELETE_DATA", "Failed delete data imageFutsalField", e, "imageFutsalField")
        }

        return result
        
    }
}
