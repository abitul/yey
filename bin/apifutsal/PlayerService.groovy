package apifutsal

import grails.transaction.Transactional


@Transactional
class PlayerService {

    def result 
    def lastUpdate = new Date()

    def showData(params) {
        try{
            print lastUpdate
            if(params.teamId){
                def team = Team.get(params.teamId as Integer)
                result = Player.findAllByTeam(team)
            }else{
                Integer offset = (params.int("page")-1) * params.int("max")
                result = params.searchValue == "" ? Player.listOrderByLastUpdate(order: "desc") : Player.findAllByPlayerNameIlike("%${params.searchValue}%",[max: params.int("max"), sort: "playerName", order: "desc", offset: offset])
            }
        }catch(e){
            print "error gettting data"
            print e
            result = [message: "failed get data player"]
        }

        return result
    }

    def saveData(params) {
        try{
            def player = new Player()
            print lastUpdate
            player.playerName = params.playerName
            player.age = params.age
            player.playerPosition = params.playerPosition
            player.contactNo = params.contactNo
            player.facebook = params.facebook
            player.instagram = params.instagram
            player.twitter = params.twitter
            player.lastUpdate = lastUpdate
            def team = Team.get(params.teamId)
            team.addToPlayers(player).save(flush: true, failOnError: true)
            result = [message: "success insert data"]
        }catch(e){
            print "error saving data"
            print e
            result = [message: "failed save data player"]
        }

        return result
    }

    def updateData(params) {
        try{
            def player = Player.get(params.id)
            print player
            player.playerName = params.playerName
            player.age = params.age
            player.playerPosition = params.playerPosition
            player.contactNo = params.contactNo
            player.facebook = params.facebook
            player.instagram = params.instagram
            player.twitter = params.twitter
            player.lastUpdate = lastUpdate
            player.save(flush: true, failOnError: true)
            result = [message: "success update data"]
        }catch(e){
            print "error updating data"
            print e
            result = [message: "failed update data player"]
        }

        return result
    }

    def deleteData(params) {
        try{
            def player = Player.get(params.id)
            player.delete()
            result = [message: "success delete"]
        }catch(e){
            print "error deleting data"
            print e
            result = [message: "failed delete data player"]
        }

        return result
    }
}

