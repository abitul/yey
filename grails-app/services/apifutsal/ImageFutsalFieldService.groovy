package apifutsal

import grails.transaction.Transactional

@Transactional
class ImageFutsalFieldService {

    // def result 
    // def lastUpdate = new Date()

    // def showData(params) {
    //     try{
    //         print lastUpdate
    //         Integer offset = (params.int("page")-1) * params.int("max")
    //         result = params.searchValue == "" ? ImageFutsal.listOrderByLastUpdate(order: "desc") : ImageFutsal.findAllByImageFutsalNameIlike("%${params.searchValue}%",[max: params.int("max"), sort: "imageName", order: "desc", offset: offset])
    //     }catch(e){
    //         print "error gettting data"
    //         print e
    //         result = [message: "failed get data image"]
    //     }

    //     return result
    // }

    // def saveData(params) {
    //     try{
    //         def image = new ImageFutsal()
    //         print lastUpdate
    //         image.imageName = params.imageName
    //         image.category = params.category
    //         image.lastUpdate = lastUpdate
    //         image.save(flush: true, failOnError: true)
    //         result = [message: "success insert data"]
    //     }catch(e){
    //         print "error saving data"
    //         print e
    //         result = [message: "failed save data image"]
    //     }

    //     return result
    // }

    // def updateData(params) {
    //     try{
    //         def image = ImageFutsal.get(params.id)
    //         print image
    //         image.imageName = params.imageName
    //         image.category = params.category
    //         image.lastUpdate = lastUpdate
    //         image.save(flush: true, failOnError: true)
    //         result = [message: "success update data"]
    //     }catch(e){
    //         print "error updating data"
    //         print e
    //         result = [message: "failed update data image"]
    //     }

    //     return result
    // }

    // def deleteData(params) {
    //     try{
    //         def image = ImageFutsal.get(params.id)
    //         image.delete()
    //         result = [message: "success delete"]
    //     }catch(e){
    //         print "error deleting data"
    //         print e
    //         result = [message: "failed delete data image"]
    //     }

    //     return result
    // }
}
