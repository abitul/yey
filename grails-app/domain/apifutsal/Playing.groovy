package apifutsal

class Playing {

    String type
    Integer idVersus
    String teamVersus
    Date lastUpdated

    static constraints = {
        id generator: 'sequence'
        type size: 1..20, blank: false, nullable: false
    }
}
