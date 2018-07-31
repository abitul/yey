package apifutsal

import grails.transaction.Transactional

@Transactional
class RegisterService {

    def result 
    def lastUpdate = new Date()
    TeamService teamService
    StadionService stadionService
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
            }else if(params.userType == "USER_STADION"){
                result = stadionService.saveData(params)
            }
        }catch(e){
            result = errorHandler.errorChecking(null, "ERROR_GET_DATA", "Failed get Data register", e, "register")
        }

    }
    
}
