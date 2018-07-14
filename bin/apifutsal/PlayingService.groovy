package apifutsal

import grails.transaction.Transactional

@Transactional
class PlayingService {

    def result 
    def lastUpdate = new Date()

    def showData(params) {
        try{
            print lastUpdate
            Integer offset = (params.int("page")-1) * params.int("max")
            result = params.searchValue == "" ? Playing.listOrderByLastUpdate(order: "desc") : Playing.findAllByPlayingNameIlike("%${params.searchValue}%",[max: params.int("max"), sort: "playingName", order: "desc", offset: offset])
        }catch(e){
            print "error gettting data"
            print e
            result = [message: "failed get data playing"]
        }

        return result
    }

    def saveData(params) {
        try{
            def playing = new Playing()
            print lastUpdate
            playing.playingName = params.playingName
            playing.typePlaying = params.typePlaying
            playing.lastUpdate = lastUpdate
            playing.save(flush: true, failOnError: true)
            result = [message: "success insert data"]
        }catch(e){
            print "error saving data"
            print e
            result = [message: "failed save data playing"]
        }

        return result
    }

    def updateData(params) {
        try{
            def playing = Playing.get(params.id)
            print playing
            playing.playingName = params.playingName
            playing.typePlaying = params.typePlaying
            playing.lastUpdate = lastUpdate
            playing.save(flush: true, failOnError: true)
            result = [message: "success update data"]
        }catch(e){
            print "error updating data"
            print e
            result = [message: "failed update data playing"]
        }

        return result
    }

    def deleteData(params) {
        try{
            def playing = Playing.get(params.id)
            playing.delete()
            result = [message: "success delete"]
        }catch(e){
            print "error deleting data"
            print e
            result = [message: "failed delete data playing"]
        }

        return result
    }
}
