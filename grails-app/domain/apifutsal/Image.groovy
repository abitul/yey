package apifutsal

class Image {

    String imageName
    Integer foreignId
    Date lastUpdate

    static constraints = {
        id generator: 'sequence'
        name size: 1..100, blank: false, nullable: false
    }
}
