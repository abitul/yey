package apifutsal

import grails.transaction.Transactional

@Transactional
class FutsalFieldService {

    def result 
    def lastUpdate = new Date()

    def showData(params) {
        try{
            print lastUpdate
            if(params.stadionId){
                def stadion = Stadion.get(params.stadionId as Integer)
                def listData = FutsalField.findAllByStadion(stadion)
                result = []
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
                        result.push(objectData)
                }
            }else{
                Integer offset = (params.int("page")-1) * params.int("max")
                result = params.searchValue == "" ? FutsalField.listOrderByLastUpdate(order: "desc") : FutsalField.findAllByNameIlike("%${params.searchValue}%",[max: params.int("max"), sort: "name", order: "desc", offset: offset])
            }
        }catch(e){
            print "error gettting data"
            print e
            result = [message: "failed get data futsalField"]
        }

        return result
    }
    
    def saveData(params) {
        try{
            def futsalField = new FutsalField()
            print lastUpdate
            futsalField.name = params.name
            futsalField.type = params.type
            futsalField.startTime = params.startTime
            futsalField.endTime = params.endTime
            futsalField.price = params.price
            futsalField.lastUpdate = lastUpdate
            def stadion = Stadion.get(params.stadionId)
            stadion.addToFutsalFields(futsalField).save(flush: true, failOnError: true)
            result = [message: "success insert data"]
        }catch(e){
            print "error saving data"
            print e
            result = [message: "failed save data futsalField"]
        }

        return result
    }

    def updateData(params) {
        try{
            def futsalField = FutsalField.get(params.id)
            print futsalField
            futsalField.name = params.name
            futsalField.type = params.type
            futsalField.startTime = params.startTime
            futsalField.endTime = params.endTime
            futsalField.price = params.price
            futsalField.lastUpdate = lastUpdate
            futsalField.save(flush: true, failOnError: true)
            result = [message: "success update data"]
        }catch(e){
            print "error updating data"
            print e
            result = [message: "failed update data futsalField"]
        }

        return result
    }

    def deleteData(params) {
        try{
            def futsalField = FutsalField.get(params.id)
            futsalField.delete()
            result = [message: "success delete"]
        }catch(e){
            print "error deleting data"
            print e
            result = [message: "failed delete data futsalField"]
        }

        return result
    }
}

