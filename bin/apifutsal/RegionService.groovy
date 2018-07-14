package apifutsal

import grails.transaction.Transactional

@Transactional
class RegionService {

    def result 
    def lastUpdate = new Date()

    def showData(params) {
        try{
            print lastUpdate
            Integer offset = (params.int("page")-1) * params.int("max")
            result = params.searchValue == "" ? Region.listOrderByLastUpdate(order: "desc") : Region.findAllByRegionNameIlike("%${params.searchValue}%",[max: params.int("max"), sort: "regionName", order: "desc", offset: offset])
        }catch(e){
            print "error gettting data"
            print e
            result = [message: "failed get data region"]
        }

        return result
    }

    def saveData(params) {
        try{
            def region = new Region()
            print lastUpdate
            region.regionName = params.regionName
            region.typeRegion = params.typeRegion
            region.lastUpdate = lastUpdate
            region.save(flush: true, failOnError: true)
            result = [message: "success insert data"]
        }catch(e){
            print "error saving data"
            print e
            result = [message: "failed save data region"]
        }

        return result
    }

    def updateData(params) {
        try{
            def region = Region.get(params.id)
            print region
            region.regionName = params.regionName
            region.typeRegion = params.typeRegion
            region.lastUpdate = lastUpdate
            region.save(flush: true, failOnError: true)
            result = [message: "success update data"]
        }catch(e){
            print "error updating data"
            print e
            result = [message: "failed update data region"]
        }

        return result
    }

    def deleteData(params) {
        try{
            def region = Region.get(params.id)
            region.delete()
            result = [message: "success delete"]
        }catch(e){
            print "error deleting data"
            print e
            result = [message: "failed delete data region"]
        }

        return result
    }
}
