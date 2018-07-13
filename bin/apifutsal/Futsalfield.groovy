package apifutsal

class Futsalfield {

    String futsalfieldName
    String typeFutsalfield
    Date lastUpdate

    static constraints = {
        id generator: 'sequence'
    }

    static mapping = {

    }
}
