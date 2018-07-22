package apifutsal

import grails.transaction.Transactional

@Transactional
class PlayingService {

    def result 
    def lastUpdate = new Date()

    def showData(params) {
        try{
            print lastUpdate
            result = []
            def startTime =  Date.parse("yyyy-MM-dd H:mm:s", params.startTime)
            def endTime = Date.parse("yyyy-MM-dd H:mm:s", params.endTime)
            def listData = params.isBattle.toBoolean() ? Booking.findAllByVersusTeamIdIsNotNullAndCreatedDateBetween(startTime, endTime) : Booking.findAllByVersusTeamIdIsNullAndCreatedDateBetween(startTime, endTime)
            println listData
            listData.each{res->
                    def stadion = Stadion.get(res.stadionId)
                    def futsalField = FutsalField.get(res.futsalFieldId)
                    def team1 = Team.get(res.teamId)
                    def team2 = res.versusTeamId ? Team.get(res.versusTeamId) : null

                    def objectData = [
                        bookingId: res.id,
                        isBattle : params.isBattle.toBoolean(),
                        team1Id: res.teamId,
                        team1Name: team1.teamName,
                        team2Id: res.versusTeamId ? res.versusTeamId : null,
                        team2Name: res.versusTeamId ? team2.teamName : null,
                        province: stadion.province,
                        districs: stadion.districs,
                        subDistrics: stadion.subDistrics,
                        kelurahan: stadion.kelurahan,
                        zipCode: stadion.zipCode,
                        futsalFieldId: res.futsalFieldId,
                        futsalFieldName: futsalField.name
                    ]

                    result.push(objectData)
            }
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
            playing.type = params.type
            playing.idVersus = params.idVersus
            playing.teamVersus = params.teamVersus
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
            playing.type = params.type
            playing.idVersus = params.idVersus
            playing.teamVersus = params.teamVersus
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
