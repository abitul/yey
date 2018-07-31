package apifutsal

import grails.transaction.Transactional

@Transactional
class FutsalFieldService {

    def result 
    def lastUpdate = new Date()
    def futsalField

    def showData(params) {

        try{
            print lastUpdate
            if(params.stadionId){
                def stadion = Stadion.get(params.stadionId as Integer)
                def listData = FutsalField.findAllByStadion(stadion)
                futsalField = []
                listData.each{res->
                        def objectData = [
                                            id: res.id,
                                            name: res.name,
                                            type: res.type,
                                            startTime: res.startTime,
                                            endTime: res.endTime,
                                            price: res.price,
                                            lastUpdate: res.lastUpdate
                                                                        ]
                        futsalField.push(objectData)
                }
            }else{
                Integer offset = (params.int("page")-1) * params.int("max")
                futsalField = params.searchValue ? FutsalField.findAllByNameIlike("%${params.searchValue}%",[max: params.int("max"), sort: "name", order: "desc", offset: offset]) : FutsalField.listOrderByLastUpdate(order: "desc") 
            }

            result = [
                data : futsalField,
                message : "success get data",
                isSuccessFull : true
            ]

        }catch(e){
            result = errorHandler.errorChecking(null, "ERROR_GET_DATA", "Failed get Data futsalField", e, "futsalField")
        }

        return result

    }
    
    def saveData(params) {

        try{
            futsalField = new FutsalField()
            print lastUpdate
            futsalField.name = params.name
            futsalField.type = params.type
            futsalField.startTime = params.startTime
            futsalField.endTime = params.endTime
            futsalField.price = params.price
            futsalField.lastUpdate = lastUpdate
            def stadion = Stadion.get(params.stadionId)
            stadion.addToFutsalFields(futsalField).save(flush: true, failOnError: true)
            result = [message: "success insert data", isSuccessFull : true]
        }catch(e){
            result = errorHandler.errorChecking(team, "ERROR_SAVE_DATA", "Error save data", e, "futsalField")
        }

        return result

    }

    def updateData(params) {

        try{
            futsalField = FutsalField.get(params.id)
            print futsalField
            futsalField.name = params.name
            futsalField.type = params.type
            futsalField.startTime = params.startTime
            futsalField.endTime = params.endTime
            futsalField.price = params.price
            futsalField.lastUpdate = lastUpdate
            futsalField.save(flush: true, failOnError: true)
            result = [message: "success update data", isSuccessFull : true]
        }catch(e){
            result = errorHandler.errorChecking(team, "ERROR_UPDATE_DATA", "Failed update data futsalField", e, "futsalField")
        }

        return result

    }

    def deleteData(params) {

        try{
            futsalField = FutsalField.get(params.id)
            futsalField.delete()
            result = [message: "success delete", isSuccessFull : true]
        }catch(e){
            result = errorHandler.errorChecking(null, "ERROR_DELETE_DATA", "Failed delete data futsalField", e, "futsalField")
        }

        return result
        
    }
}

