package User.Agent;

import User.Patient.PatientController;

import static helper.SystemeHelper.println;
import static helper.SystemeHelper.scan;

public class Agent{
    private long id;
    private String email;
    private String passw;

    AgentController agentController;
    public Agent () {
        agentController = new AgentController();
    }

    public void authenticate(){
        println("**** \t\t Login Page \t\t ***");
        println("Enter your email :");
        this.email = scan().nextLine();
        println("Enter your password :");
        this.passw = scan().nextLine();
        if(agentController.authenticate(email, passw)){
            println("Successfully authenticated!");
        } else {
            println("Failed authentication. Either invalid credentials or patient not existant!");
        };
    }

    public void disconnect(){
        agentController.closeDBConnection();
    }
}
