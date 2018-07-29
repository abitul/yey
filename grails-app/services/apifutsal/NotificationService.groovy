package apifutsal

import grails.transaction.Transactional

@Transactional
class NotificationService {

    def result 
    def lastUpdate = new Date()

    def showData(params) {
        try{
            print lastUpdate
            if(params.notificationId){
                def notification = Notification.get(params.notificationId as Integer)
                result = Notification.findAllByNotification(notification)
            }else{
                Integer offset = (params.int("page")-1) * params.int("max")
                result = params.searchValue == "" ? Notification.listOrderByLastUpdate(order: "desc") : Notification.findAllByNotificationNameIlike("%${params.searchValue}%",[max: params.int("max"), sort: "notificationName", order: "desc", offset: offset])
            }
        }catch(e){
            print "error gettting data"
            print e
            result = [message: "failed get data notification"]
        }

        return result
    }

    def saveData(params) {
        try{
            def notification = new Notification()
            print lastUpdate
            notification.title = params.title
            notification.message = params.message
            notification.category = params.category
            notification.payload = params.payload
            notification.isRead = params.isRead
            notification.isNeedAction = params.isNeedAction
            notification.isCompleteAction = params.isCompleteAction
            notification.userId = params.userId
            notification.lastUpdate = lastUpdate
            notification.save(flush: true, failOnError: true)
            result = [message: "success insert data"]
        }catch(e){
            print "error saving data"
            print e
            result = [message: "failed save data notification"]
        }

        return result
    }

    def updateData(params) {
        try{
            def notification = Notification.get(params.id)
            print notification
            notification.title = params.title
            notification.message = params.message
            notification.category = params.category
            notification.payload = params.payload
            notification.isRead = params.isRead
            notification.isNeedAction = params.isNeedAction
            notification.isCompleteAction = params.isCompleteAction
            notification.userId = params.userId
            notification.lastUpdate = lastUpdate
            notification.save(flush: true, failOnError: true))
            result = [message: "success update data"]
        }catch(e){
            print "error updating data"
            print e
            result = [message: "failed update data notification"]
        }

        return result
    }

    def deleteData(params) {
        try{
            def notification = Notification.get(params.id)
            notification.delete()
            result = [message: "success delete"]
        }catch(e){
            print "error deleting data"
            print e
            result = [message: "failed delete data notification"]
        }

        return result
    }
}
