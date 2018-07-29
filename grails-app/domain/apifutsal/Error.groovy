package apifutsal

class Error {

    String errorTag
    String errorMessage
    String errorField
    String tableName

    static constraints = {
        id generator: 'sequence'
        errorTag size: 1..100, blank: false, nullable: false
        errorMessage size: 1..200, blank: false, nullable: false
        errorField size: 1..50, blank: false, nullable: false
        tableName size: 1..50, blank: false, nullable: false
    }

    static mapping = {

    }

}
