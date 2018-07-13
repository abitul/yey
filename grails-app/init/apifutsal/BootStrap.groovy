package apifutsal

class BootStrap {

    def springSecurityService

    def init = { servletContext ->
        Role roleUser = new Role(authority: 'ROLE_ADMIN').save(flush: true)

        User user = new User(
            username: 'abit',
            password: springSecurityService.encodePassword('p@ssw0rd') ,
            enabled: true,
            accountExpired: false,
            accountLocked: false,
            passwordExpired: false
        ).save(flush: true)

        UserRole.create(user, roleUser)
    }
    def destroy = {
    }
}
