package apifutsal

import grails.transaction.Transactional

@Transactional
class DeviceNotificationService {

    def result 
    def lastUpdate = new Date()
    def deviceNotification
    ErrorHandler errorHandler

    def showData(params) {
        
        try{
            print lastUpdate
            if(params.deviceNotificationId){
                deviceNotification = DeviceNotification.get(params.deviceNotificationId as Integer)
            }else{
                Integer offset = (params.int("page")-1) * params.int("max")
                deviceNotification = params.searchValue ? DeviceNotification.findAllByDeviceNotificationNameIlike("%${params.searchValue}%",[max: params.int("max"), sort: "deviceNotificationName", order: "desc", offset: offset]) : DeviceNotification.listOrderByLastUpdate(order: "desc") 
            }

            result = [ 
                data : deviceNotification,
                message : "success get data",
                isSuccessFull : true 
            ] 

        }catch(e){
            result = errorHandler.errorChecking(null, "ERROR_GET_DATA", "Failed get Data deviceNotification", e, "deviceNotification")
        }

        return result

    }

    def saveData(params) {

        try{
            deviceNotification = new DeviceNotification()
            print lastUpdate
            deviceNotification.userId = params.userId
            deviceNotification.deviceName = params.deviceName
            deviceNotification.deviceToken = params.deviceToken
            deviceNotification.enabled = params.enabled
            deviceNotification.lastUpdate = lastUpdate
            deviceNotification.save(flush: true, failOnError: true)
            result = [message: "success insert data", isSuccessFull : true]
        }catch(e){
            result = errorHandler.errorChecking(deviceNotification, "ERROR_SAVE_DATA", "Error save data", e, "deviceNotification")
        }

        return result

    }

    def updateData(params) {

        try{
            deviceNotification = DeviceNotification.get(params.id)
            print deviceNotification
            deviceNotification.userId = params.userId
            deviceNotification.deviceName = params.deviceName
            deviceNotification.deviceToken = params.deviceToken
            deviceNotification.enabled = params.enabled
            deviceNotification.lastUpdate = lastUpdate
            deviceNotification.save(flush: true, failOnError: true)
            result = [message: "success update data", isSuccessFull : true]
        }catch(e){
            result = errorHandler.errorChecking(deviceNotification, "ERROR_UPDATE_DATA", "Failed update data deviceNotification", e, "deviceNotification")
        }

        return result

    }

    def deleteData(params) {

        try{
            deviceNotification = DeviceNotification.get(params.id)
            deviceNotification.delete()
            result = [message: "success delete", isSuccessFull : true]
        }catch(e){
            result = errorHandler.errorChecking(null, "ERROR_DELETE_DATA", "Failed delete data deviceNotification", e, "deviceNotification")
        }

        return result
        
    }
}
