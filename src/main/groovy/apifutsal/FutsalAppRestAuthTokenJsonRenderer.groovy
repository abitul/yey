package apifutsal

import grails.plugin.springsecurity.rest.token.AccessToken
import grails.plugin.springsecurity.rest.token.rendering.AccessTokenJsonRenderer
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.util.Assert
import groovy.json.JsonBuilder
import grails.transaction.Transactional

@Transactional
class FutsalAppRestAuthTokenJsonRenderer implements AccessTokenJsonRenderer {

    @Override
    String generateJson(AccessToken accessToken){
        def response = new AuthResponse(
                userId: accessToken.principal.id,   
                userType: User.get(accessToken.principal.id)?.userType,
                username: accessToken.principal.username,
                access_token: accessToken.accessToken,
                roles: accessToken.authorities.collect { GrantedAuthority role -> role.authority },
                expiration: accessToken.expiration,
                refreshToken : accessToken.refreshToken
        )

        return new JsonBuilder( response ).toPrettyString()
    }
}

class AuthResponse {
    Integer userId
    String userType
    String username
    String access_token
    Collection roles
    Integer expiration
    String refreshToken
}

