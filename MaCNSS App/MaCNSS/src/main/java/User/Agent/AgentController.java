package User.Agent;

public class AgentController {


    AgentModel agentModel;
    public AgentController () {
        agentModel = new AgentModel();
    }

    public boolean authenticate (String email, String passw) {
        boolean isValid = agentModel.isAgentExist(email, passw);
        agentModel.closeQuery();
        return isValid;
    }


    public void closeDBConnection () {
        agentModel.closeDBConnection();
    }

}
