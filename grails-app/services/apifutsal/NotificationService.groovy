package apifutsal

import grails.transaction.Transactional

@Transactional
class NotificationService {

    def result 
    def lastUpdate = new Date()
    def notification

    def showData(params) {

        try{
            print lastUpdate
            if(params.notificationId){
                notification = Notification.get(params.notificationId as Integer)
            }else{
                Integer offset = (params.int("page")-1) * params.int("max")
                notification = params.searchValue  ? Notification.findAllByNotificationNameIlike("%${params.searchValue}%",[max: params.int("max"), sort: "notificationName", order: "desc", offset: offset]) : Notification.listOrderByLastUpdate(order: "desc") 
            }

            result = [
                data : notification,
                message : "success get data",
                isSuccessFull : true
            ]

        }catch(e){
            result = errorHandler.errorChecking(null, "ERROR_GET_DATA", "Failed get Data notification", e, "notification")
        }

        return result

    }

    def saveData(params) {

        try{
            notification = new Notification()
            print lastUpdate
            notification.title = params.title
            notification.message = params.message
            notification.category = params.category
            notification.payload = params.payload
            notification.isRead = params.isRead
            notification.isNeedAction = params.isNeedAction
            notification.isCompleteAction = params.isCompleteAction
            notification.isPushNotification = params.isPushNotification
            notification.userId = params.userId
            notification.lastUpdate = lastUpdate
            notification.save(flush: true, failOnError: true)
            result = [message: "success insert data", isSuccessFull : true]
        }catch(e){
            result = errorHandler.errorChecking(team, "ERROR_SAVE_DATA", "Error save data", e, "notification")
        }

        return result

    }

    def updateData(params) {

        try{
            notification = Notification.get(params.id)
            print notification
            notification.title = params.title
            notification.message = params.message
            notification.category = params.category
            notification.payload = params.payload
            notification.isRead = params.isRead
            notification.isNeedAction = params.isNeedAction
            notification.isCompleteAction = params.isCompleteAction
            notification.isPushNotification = params.isPushNotification
            notification.userId = params.userId
            notification.lastUpdate = lastUpdate
            notification.save(flush: true, failOnError: true)
            result = [message: "success update data", isSuccessFull : true]
        }catch(e){
            result = errorHandler.errorChecking(team, "ERROR_UPDATE_DATA", "Failed update data notification", e, "notification")
        }

        return result

    }

    def deleteData(params) {

        try{
            notification = Notification.get(params.id)
            notification.delete()
            result = [message: "success delete", isSuccessFull : true]
        }catch(e){
            result = errorHandler.errorChecking(null, "ERROR_DELETE_DATA", "Failed delete data notification", e, "notification")
        }

        return result
        
    }
}
