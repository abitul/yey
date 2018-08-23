
grails.plugin.springsecurity.useSecurityEventListener = true
grails.plugin.springsecurity.rest.login.active = true
grails.plugin.springsecurity.rest.login.endpointUrl = "/api/login"
grails.plugin.springsecurity.rest.login.failureStatusCode = 401
grails.plugin.springsecurity.rest.login.useJsonCredentials = true
grails.plugin.springsecurity.rest.login.usernamePropertyName = "username"
grails.plugin.springsecurity.rest.login.passwordPropertyName = "password"
grails.plugin.springsecurity.rest.login.useRequestParamsCredentials = false
grails.plugin.springsecurity.rest.logout.endpointUrl = "/api/logout"
grails.plugin.springsecurity.rest.token.validation.active = true
grails.plugin.springsecurity.rest.token.validation.endpointUrl = "/api/validate"

grails.plugin.springsecurity.filterChain.chainMap = [
    [pattern: '/api/**',       filters: 'JOINED_FILTERS,-anonymousAuthenticationFilter,-exceptionTranslationFilter,-authenticationProcessingFilter,-securityContextPersistenceFilter,-rememberMeAuthenticationFilter'],
    [pattern: '/**',           filters: 'JOINED_FILTERS,-restTokenValidationFilter,-restExceptionTranslationFilter'],
    [pattern: '/register/**',  filters: 'anonymousAuthenticationFilter,restTokenValidationFilter,restExceptionTranslationFilter,filterInvocationInterceptor'],
    [pattern: '/disclaimer/**',  filters: 'anonymousAuthenticationFilter,restTokenValidationFilter,restExceptionTranslationFilter,filterInvocationInterceptor']
]

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'apifutsal.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'apifutsal.UserRole'
grails.plugin.springsecurity.authority.className = 'apifutsal.Role'

grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	[pattern: '/',               access: ['permitAll']],
    [pattern: '/error',          access: ['permitAll']],
    [pattern: '/index',          access: ['permitAll']],
    [pattern: '/index.gsp',      access: ['permitAll']],
    [pattern: '/shutdown',       access: ['permitAll']],
    [pattern: '/assets/**',      access: ['permitAll']],
    [pattern: '/**/js/**',       access: ['permitAll']],
    [pattern: '/**/css/**',      access: ['permitAll']],
    [pattern: '/**/images/**',   access: ['permitAll']],
    [pattern: '/**/favicon.ico', access: ['permitAll']],
    [pattern: '/api/login',      access: ['permitAll']],
    [pattern: '/register/**',    access: ['permitAll']],
    [pattern: '/disclaimer/**',    access: ['permitAll']],
    [pattern: '/api/validate',   access: ['isFullyAuthenticated()']],
    [pattern: '/api/logout',     access: ['isFullyAuthenticated()']],
    [pattern: '/api/**',         access: ['isFullyAuthenticated()']],
    [pattern: '/**',             access: ['isFullyAuthenticated()']]
]

grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/assets/**',      filters: 'none'],
	[pattern: '/**/js/**',       filters: 'none'],
	[pattern: '/**/css/**',      filters: 'none'],
	[pattern: '/**/images/**',   filters: 'none'],
	[pattern: '/**/favicon.ico', filters: 'none'],
    [pattern: '/register/**',    filters: 'none'],
    [pattern: '/disclaimer/**',    filters: 'none'],
	[pattern: '/**',             filters: 'JOINED_FILTERS']
]

asynchronous.mail.default.attempt.interval=300000l      // Five minutes
asynchronous.mail.default.max.attempts.count=1
asynchronous.mail.send.repeat.interval=60000l           // One minute
asynchronous.mail.expired.collector.repeat.interval=607000l
asynchronous.mail.messages.at.once=100
asynchronous.mail.send.immediately=true
asynchronous.mail.clear.after.sent=false
asynchronous.mail.disable=false
asynchronous.mail.useFlushOnSave=true
asynchronous.mail.persistence.provider='hibernate4'     // Possible values are 'hibernate', 'hibernate4', 'mongodb'
asynchronous.mail.newSessionOnImmediateSend=false
asynchronous.mail.taskPoolSize=1