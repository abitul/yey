package apifutsal

class Region {

    String province
    String districs
    String subDistrics
    String kelurahan
    Integer zipCode
    Date lastUpdated

    static constraints = {
        id generator: 'sequence'
        province size: 1..100, blank: false, nullable: false
        districs size: 1..100, blank: false, nullable: false
        subDistrics size: 1..100, blank: false, nullable: false
        kelurahan size: 1..100, blank: false, nullable: false
        zipCode size: 1..10, blank: false, nullable: false
    }
}
