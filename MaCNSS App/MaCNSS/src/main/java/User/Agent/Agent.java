package User.Agent;

import User.Patient.PatientController;
import helper.Emailer.EmailHelper;
import helper.Emailer.SimpleEmail;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import static helper.SystemeHelper.*;

public class Agent{
    private long id;
    private String email;
    private String passw;

    AgentController agentController;
    public Agent () {
        agentController = new AgentController();
    }

    public boolean authenticate(){
        boolean isAllValid = false;
        println("**** \t\t Login Page \t\t ***");
        println("Enter your email :");
        this.email = scan().nextLine();
        println("Enter your password :");
        this.passw = scan().nextLine();
        if(agentController.authenticate(email, passw)){
            //demand from user to enter verification code / Send email
            if(sendVerificationEmail()){
                println("Successfully authenticated!");
                isAllValid = true;
            }else {
                println("Invalid verification code. Failed authentication!");
            }
        } else {
            println("Failed authentication. Either invalid credentials or patient not existant!");
        };
        
        return isAllValid;
    }
    public boolean sendVerificationEmail () {
        String codeVerification = EmailHelper.codeGenerator();
        //provide email subject
        String verificationSbj = "MaCNSS login verification ";
        //provide email body msg
        String codeVerificaitonMsg = "<h3>Code vérification :</h3>" + codeVerification;
        //Send email with verification code
        LocalTime sentTime = SimpleEmail.sendSimpleEmail(email, verificationSbj, codeVerificaitonMsg);

        println("Enter verification code : ");
        String code;
        do{
            code = scan().nextLine();;
            if(code.equals(codeVerification)){
                return true;
            }
            println("Re-enter verification code : ");
        } while(isCodeValid(sentTime));

        return false;
    }

    public boolean isCodeValid(LocalTime sentTime){
        if(sentTime.until(LocalTime.now(), ChronoUnit.MINUTES) <= 5) return true;
        return false;
    }

    public int menuDossier(){
        println("####################### Menu Dossier #######################");
        println("1. Entre un nouveau dossier");
        println("2. Voire liste des dossiers");
        return scan().nextInt();
    }

    public void disconnect(){
        agentController.closeDBConnection();
    }
}
