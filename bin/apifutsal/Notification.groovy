package apifutsal

class Notification {

    String title
    String message
    String category
    String payload
    boolean isRead
    boolean isNeedAction
    boolean isCompleteAction
    boolean isPushNotification
    Integer userId
    Date lastUpdate

    static constraints = {
        id generator: 'sequence'
        title size: 1..50, blank: false, nullable: false
        message size: 1..200, blank: false, nullable: false
        category size: 1..30, blank: false, nullable: false
        payload size: 1..500, blank: false, nullable: false
        isRead size: 1..10, blank: false, nullable: false
        isNeedAction size: 1..10, blank: false, nullable: false
        isCompleteAction size: 1..10, blank: false, nullable: false
        userId size: 1..10, blank: false, nullable: false
    }

    static mapping = {

    }
}
