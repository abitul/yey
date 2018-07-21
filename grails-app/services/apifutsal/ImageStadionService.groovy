package apifutsal

import grails.transaction.Transactional

@Transactional
class ImageStadionService {

    def result 
    def lastUpdate = new Date()
    def filePath
    def grailsApplication 
    ImageEncrypter imageEncrypter 

    def showData(params) {
        try{
            println lastUpdate
            if(params.stadionId){
                def stadion = Stadion.get(params.stadionId as Integer)
                def listData = ImageStadion.findAllByStadion(stadion)
                print listData
                result = []
                listData.each{res->
                    def objectData = [ 
                                        id : res.id,
                                        category : res.category,
                                        imageName : res.imageName,
                                        base64Image : imageEncrypter.getBase64File(grailsApplication.config.properties.imageStadionPath+"\\${res.imageName}"),
                                        stadionId : res.stadionId ]
                    result.push(objectData)
                }
            }else{
                Integer offset = (params.int("page")-1) * params.int("max")
                result = params.searchValue == "" ? ImageStadion.listOrderByLastUpdate(order: "desc") : ImageStadion.findAllByImageNameIlike("%${params.searchValue}%",[max: params.int("max"), sort: "imageName", order: "desc", offset: offset])
            } 
        }catch(e){
            print "error gettting data"
            print e
            result = [message: "failed get data image"]
        }

        return result
    }

    def saveData(params) {
        try{
            def image = new ImageStadion()
            print lastUpdate
            image.imageName = params.imageName
            filePath = grailsApplication.config.properties.imageStadionPath+"\\${params.imageName}"
            if(params.base64Image && params.base64Image!=""){
                imageEncrypter.saveBase64ToFile(params.base64Image, filePath)
            }
            image.category = params.category
            image.lastUpdate = lastUpdate
            def stadion = Stadion.get(params.stadionId)
            stadion.addToImagesStadion(image).save(flush: true, failOnError: true)
            result = [message: "success insert data"]
        }catch(e){
            print "error saving data"
            print e
            result = [message: "failed save data image"]
        }

        return result
    }

    def updateData(params) {
        try{
            def image = ImageStadion.get(params.id)
            print image
            image.imageName = params.imageName
            filePath = grailsApplication.config.properties.imageStadionPath+"\\${params.imageName}"
            if(params.base64Image && params.base64Image!=""){
                imageEncrypter.saveBase64ToFile(params.base64Image, filePath)
            }
            image.category = params.category
            image.lastUpdate = lastUpdate
            image.save(flush: true, failOnError: true)
            result = [message: "success update data"]
        }catch(e){
            print "error updating data"
            print e
            result = [message: "failed update data image"]
        }

        return result
    }

    def deleteData(params) {
        try{
            def image = ImageStadion.get(params.id)
            image.delete()
            result = [message: "success delete"]
        }catch(e){
            print "error deleting data"
            print e
            result = [message: "failed delete data image"]
        }

        return result
    }
}
