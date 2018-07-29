package apifutsal

import grails.transaction.Transactional

@Transactional
class SendemailService {

    def sendDirectEmail(params) {
        sendMail {
                to params.to
                subject params.subject
                text params.body
        }
    }

    def sendEmailConfirmation(params) {
        emailConfirmationService.sendConfirmation(
            to: params.to,
            subject: "Registration Confirm",
            id: params.userId)
    }

    // // Send a simple confirmation
    // emailConfirmationService.sendConfirmation(
    //     to:params.email,
    //     subject:"Please confirm!")
    
    // // Send a confirmation with custom from: address
    // emailConfirmationService.sendConfirmation(
    //     from:'helpdesk@mycorp.com',
    //     to:params.email,
    //     subject:"Please confirm!")
    
    // // Send a confirmation with custom email template
    // emailConfirmationService.sendConfirmation(
    //     view:'/mailtemplates/confirm_signup',
    //     to:params.email,
    //     subject:"Please confirm!")
    
    // // Send a confirmation with custom email template and model
    // emailConfirmationService.sendConfirmation(
    //     model:[account:userAccount, promoCode:'NEWSIGNUP'],
    //     view:'/mailtemplates/confirm_signup', 
    //     to:params.email,
    //     subject:"Please confirm!")
    
    // // Send a confirmation with application id data
    // emailConfirmationService.sendConfirmation(
    //     to:params.email,
    //     subject:"Please confirm!",
    //     id:userAccount.ident())
    
    // // Send a confirmation with custom event namespace
    // emailConfirmationService.sendConfirmation(
    //     to:params.email,
    //     subject:"Please confirm!",
    //     eventNamespace:'plugin.myPlugin')
    
    // // Send a confirmation with custom event prefix and namespace
    // // The event topic would be 'signup.confirmed' in the specified namespace
    // emailConfirmationService.sendConfirmation(
    //     to:params.email,
    //     subject:"Please confirm!",
    //     event:'signup',
    //     eventNamespace:'plugin.myPlugin')

    // Standard confirmed event and namespace
    // @grails.events.Listener(topic:'confirmed', namespace:'plugin.emailConfirmation') 
    // def userConfirmed(info) {
    //     log.info "User ${info.email} successfully confirmed with application id data ${info.id}"
    //     // return [controller:'userProfile', action:'welcomeNewUser']
    // }
 
    // Standard timeout event and namespace
    // @Listener(topic:'timeout', namespace:'plugin.emailConfirmation') 
    // def userConfirmationTimedOut(info) {
    //     log.info "A user failed to confirm, the token in their link was ${info.token}"
    // }
 
    // // Standard timeout event and namespace
    // @Listener(topic:'invalid', namespace:'plugin.emailConfirmation') 
    // def userConfirmationWasInvalid(info) {
    //     log.info "User ${info.email} failed to confirm for application id data ${info.id}"
    //     // return [controller:'userProfile', action:'invalidConfirmationHelp']
    // }
 
    // // Custom confirmed event prefix and namespace set to 'app' when requesting confirmation
    // @Listener(topic:'registration.confirmed') 
    // def registrationConfirmed(info) {
    //     log.info "User ${info.email} successfully confirmed with application id data ${info.id}"
    //     // return [controller:'userProfile', action:'welcomeNewUser']
    // }
 
    // // Custom confirmed event prefix and using a custom plugin namespace
    // @Listener(topic:'changePassword.confirmed', namespace:'plugin.mySecurity') 
    // def changePasswordConfirmed(info) {
    //     log.info "User ${info.email} successfully confirmed with application id data ${info.id}"
    //     // return [controller:'security', action:'doChangePassword']
    // }
}
