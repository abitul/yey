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
            if (params.userId){
                stadion = Stadion.findByUserId(params.userId as Integer)
            }else{
                Integer offset = (params.int("page")-1) * params.int("max")
                stadion = params.searchValue  ? Stadion.findAllByDistrictsIlike("%${params.searchValue}%",[max: params.int("max"), sort: "stadionName", order: "desc", offset: offset]) : Stadion.listOrderByLastUpdate(order: "desc") 
            }

            result = [
                data : mappingResponse(stadion),
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
            result = errorHandler.errorChecking(stadion, "ERROR_SAVE_DATA", "Error save data", e, "stadion")
        }

        return result

    }

    def updateData(params) {

        try{
            stadion = Stadion.get(params.id)
            saveToDB(stadion,params)
            result = [message: "success update data", isSuccessFull : true]
        }catch(e){
            result = errorHandler.errorChecking(stadion, "ERROR_UPDATE_DATA", "Failed update data stadion", e, "stadion")
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
            stadion.districts = params.districts
            stadion.subDistricts = params.subDistricts
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
            stadion.userId = params.userId
            filePath = grailsApplication.config.properties.profileStadionPath+"\\${params.imageProfile}"
            if(params.base64Image && params.base64Image!=""){
                imageEncrypter.saveBase64ToFile(params.base64Image, filePath)
            }
            stadion.lastUpdate = lastUpdate
            stadion.save(flush: true, failOnError: true)
            
    }

    def mappingResponse(stadion){

            def listData = []

            stadion.each{  res->

                def objectData = [
                    id : res.id,
                    stadionName: res.stadionName,
                    idCard : res.idCard,
                    province : res.province,
                    districts : res.districts,
                    subDistricts : res.subDistricts,
                    kelurahan : res.kelurahan,
                    zipCode : res.zipCode,
                    adress : res.adress,
                    contactNo : res.contactNo,
                    guard : res.guard,
                    imageProfile : res.imageProfile,
                    base64Image : imageEncrypter.getBase64File(grailsApplication.config.properties.profileStadionPath+"\\${res.imageProfile}"),
                    countfutsalField :  res.countfutsalField,
                    email : res.email,
                    facebook : res.facebook,
                    instagram : res.instagram,
                    twitter : res.twitter,
                    facilities : res.facilities,
                    userId : res.userId
                ]

                listData.push(objectData)

            }
            

            return listData
    }


    def testya(){
        return "sfsfs"
    }
}

