package apifutsal

import grails.transaction.Transactional
import grails.plugin.asyncmail.AsynchronousMailService

@Transactional
class SendemailService {
    
    AsynchronousMailService asyncMailService
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

    def sendEmailAsync(params){
        asyncMailService.sendMail {
            // Mail parameters
            to params.to;
            subject params.subject;
            html "<body><u>${params.body}</u></body>"
            // attachBytes 'test.txt', 'text/plain', byteBuffer;

            // Additional asynchronous parameters (optional)
            beginDate new Date(System.currentTimeMillis()+500)    // Starts after one minute, default current date
            endDate new Date(System.currentTimeMillis()+3600000)   // Must be sent in one hour, default infinity
            maxAttemptsCount 3;   // Max 3 attempts to send, default 1
            attemptInterval 300000;    // Minimum five minutes between attempts, default 300000 ms
            delete true;    // Marks the message for deleting after sent
            immediate true;    // Run the send job after the message was created
            priority 10;   // If priority is greater then message will be sent faster
        }
    }

}
