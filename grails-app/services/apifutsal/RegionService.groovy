package apifutsal

import grails.transaction.Transactional

@Transactional
class RegionService {

    def result 
    def lastUpdate = new Date()

    def showData(params) {
        try{
            print lastUpdate

            def criteriaRegion = Region.createCriteria()
            result = [ "${params.searchFor}" : criteriaRegion.list{
                        
                        if(params.searchValue && params.searchValue !=""){
                            ilike ("${params.searchBy}","%${params.searchValue}%")
                        }
                        projections{
                            groupProperty("${params.searchFor}")
                        }

                    order("${params.searchFor}", "asc")
            }]

        }catch(e){
            print "error gettting data"
            print e
            result = [message: "failed get data region"]
        }

        return result
    }

}
