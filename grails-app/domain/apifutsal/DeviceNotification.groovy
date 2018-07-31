package apifutsal

class DeviceNotification {

    Integer userId
    String deviceName
    String deviceToken
    boolean enabled
    Date lastUpdate

    static constraints = {
        id generator: 'sequence'
        userId size: 1..10, blank: false, nullable: false
        deviceName size: 1..50, blank: false, nullable: false
        deviceToken size: 1..100, blank: false, nullable: false
        enabled size: 1..10, blank: false, nullable: false
    }

    static mapping = {

    }
}
