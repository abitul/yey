package apifutsal

class Disclaimer {

    String dislcaimerTag
    String disclaimerMessage
    String module
    boolean enabled
    Date lastUpdate

    static constraints = {
        id generator: 'sequence'
        dislcaimerTag size: 1..100, blank: false, nullable: false
        disclaimerMessage size: 1..500, blank: false, nullable: false
        module size: 1..50, blank: false, nullable: false
        enabled size: 1..10, blank: false, nullable: false
    }

    static mapping = {

    }
}
