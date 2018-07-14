package apifutsal

class Sosmed {

    String sosmedName
    String type
    Date lastUpdate
    static belongsTo = [team: Team, stadion: Stadion, player: Player]

    static constraints = {
        id generator: 'sequence'
        name size: 1..100, blank: false, nullable: false
        type size: 5..20, blank: false, nullable: false
    }
}
