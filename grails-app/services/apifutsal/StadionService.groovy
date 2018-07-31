package apifutsal

import grails.transaction.Transactional


@Transactional
class StadionService {

    def result 
    def lastUpdate = new Date()
    def filePath
    def grailsApplication 
    def stadion
    ErrorHandler errorHandler

    ImageEncrypter imageEncrypter 
    
    def showData(params) {

        try{
            print lastUpdate
            if (params.id){
                stadion = Stadion.get(params.id as Integer)
                stadion = [   
                            stadionName : stadion.stadionName,
                            idCard : stadion.idCard,
                            province : stadion.province,
                            districs : stadion.districs,
                            subDistrics : stadion.subDistrics,
                            kelurahan : stadion.kelurahan,
                            zipCode : stadion.zipCode,
                            adress : stadion.adress,
                            contactNo : stadion.contactNo,
                            guard : stadion.guard,
                            imageProfile : stadion.imageProfile,
                            base64Image : imageEncrypter.getBase64File(grailsApplication.config.properties.profileStadionPath+"\\${stadion.imageProfile}"),
                            countfutsalField :  stadion.countfutsalField,
                            email : stadion.email,
                            facebook : stadion.facebook,
                            instagram : stadion.instagram,
                            twitter : stadion.twitter,
                            facilities : stadion.facilities    ]
            }else{
                Integer offset = (params.int("page")-1) * params.int("max")
                stadion = params.searchValue  ? Stadion["findAllBy${params.searchBy}Ilike"]("%${params.searchValue}%",[max: params.int("max"), sort: "stadionName", order: "desc", offset: offset]) : Stadion.listOrderByLastUpdate(order: "desc") 
            }

            result = [
                data : stadion,
                message : "success get data",
                isSuccessFull : true
            ]
            
        }catch(e){
            result = errorHandler.errorChecking(null, "ERROR_GET_DATA", "Failed get Data stadion", e, "stadion")
        }

        return result

    }

    def saveData(params) {

        try{
            stadion = new Stadion()
            saveToDB(stadion,params)
            result = [message: "success insert data", isSuccessFull : true]
        }catch(e){
            result = errorHandler.errorChecking(team, "ERROR_SAVE_DATA", "Error save data", e, "stadion")
        }

        return result

    }

    def updateData(params) {

        try{
            stadion = Stadion.get(params.id)
            saveToDB(stadion,params)
            result = [message: "success update data", isSuccessFull : true]
        }catch(e){
            result = errorHandler.errorChecking(team, "ERROR_UPDATE_DATA", "Failed update data stadion", e, "stadion")
        }

        return result

    }

    def deleteData(params) {

        try{
            stadion = Stadion.get(params.id)
            stadion.delete()
            result = [message: "success delete", isSuccessFull : true]
        }catch(e){
            result = errorHandler.errorChecking(null, "ERROR_DELETE_DATA", "Failed delete data stadion", e, "stadion")
        }

        return result

    }

    def saveToDB(stadion,params){

            stadion.stadionName = params.stadionName
            stadion.idCard = params.idCard
            stadion.province = params.province
            stadion.districs = params.districs
            stadion.subDistrics = params.subDistrics
            stadion.kelurahan = params.kelurahan
            stadion.zipCode = params.zipCode
            stadion.adress = params.adress
            stadion.contactNo = params.contactNo
            stadion.guard = params.guard
            stadion.imageProfile = params.imageProfile
            stadion.countfutsalField =  params.countfutsalField
            stadion.email = params.email
            stadion.facebook = params.facebook
            stadion.instagram = params.instagram
            stadion.twitter = params.twitter
            stadion.facilities = params.facilities
            filePath = grailsApplication.config.properties.profileStadionPath+"\\${params.imageProfile}"
            if(params.base64Image && params.base64Image!=""){
                imageEncrypter.saveBase64ToFile(params.base64Image, filePath)
            }
            stadion.lastUpdate = lastUpdate
            stadion.save(flush: true, failOnError: true)
            
    }
}

