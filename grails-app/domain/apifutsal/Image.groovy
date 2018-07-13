package apifutsal

class Image {

    String name
    String tag
    Date lastUpdated

    static constraints = {
        id generator: 'sequence'
        name size: 1..100, blank: false, nullable: false
        tag size: 1..20, blank: false, nullable: false
    }
}
