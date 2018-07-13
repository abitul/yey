package apifutsal

class Stadion {

    String stadionName
    String location
    String contactNo
    String owner
    Date lastUpdate

    static constraints = {
        id generator: 'sequence'
    }

    static mapping = {

    }
}
