package apifutsal

class Image {

    String name
    String tag
    Date lastUpdate

    static constraints = {
        id generator: 'sequence'
        name size: 1..100, blank: false, nullable: false
        tag size: 1..20, blank: false, nullable: false
    }
}
