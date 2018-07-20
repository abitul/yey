package apifutsal

import grails.transaction.Transactional

@Transactional
class RegisterService {

    def result 
    def lastUpdate = new Date()
    TeamService teamService
    def springSecurityService

    def saveData(params) {
        try{

            Role roleUser = new Role(authority: params.roleUser).save(flush: true)

            User user = new User(
                username: params.username,
                password: springSecurityService.encodePassword("${params.password}"),
                userType: params.userType,
                enabled: true,
                accountExpired: false,
                accountLocked: false,
                passwordExpired: false
            ).save(flush: true)

            UserRole.create(user, roleUser)
            def userId = User.findByUsername("${params.username}").id
            params.userId = userId
            if (params.userType == "USER_TEAM"){
                result = teamService.saveData(params)
            }else{
                
            }
        }catch(e){
            print "error saving data"
            print e
        }
    }
}
