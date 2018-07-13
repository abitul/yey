package apifutsal

class Team {

    String teamName
    Long countTeam
    String contactNo
    String imageName
    Date lastUpdate 

    static constraints = {
        id generator: 'sequence'
    }

    static mapping = {

    }
}
