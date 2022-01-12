package projectai;

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import javafx.scene.control.TextField;
import static projectai.FXMLDocumentController.q;
import static projectai.ProjectAI.*;

public class Answer extends Agent {

    String ans = "default";
    public static String object1="default", object2="default";
    public static String words[] = new String[15];
    static TextField answer1;

    @Override
    protected void setup() {
        ans = "default";
        object1="default";
        object2="default";
        Object[] ob = getArguments();

        for(int i=0; i<6; ++i)
            words[i]="";
        
        //////////////////////////////Answer the question///////////////////////////
        int sz=q.length();
        q=q.toLowerCase();
        int j=0, num=0;
        String sub="";
        for(int i=0; i<sz; ++i){
            if(i==sz-1){
                if(q.charAt(i)>='a'&&q.charAt(i)<='z'){
                    sub = q.substring(j, i+1);
                }
                else{
                    sub = q.substring(j, i);
                }
            }
            else if(q.charAt(i)==' '){
                sub = q.substring(j, i);
                j=i+1;
            }
            
            if(q.charAt(i)==' '||i==sz-1){ 
                if( sub.equals(" ") || sub.equals("") || sub.equals("?") || sub.equals(".") ){
                }
                else
                    words[num++]=sub;
            }    
        }
        
        if( !(words[5].equals("")) || (words[0].equals("")) || (words[1].equals("")) || (words[2].equals("")) || (words[3].equals("")) )
            ans="I can't explain this question";
        else{
            boolean good=true;
            ///////////////////////////////////////Sort of questions////////////////////////////////////
            if(words[0].equals("is") || words[0].equals("am")){
                if(words[3].equals("mobile")){
                    for(int i=0; i<3; ++i){
                        if(category[mobiles][i].equals(words[1])){
                            ans="Yes";
                            break;
                        }
                    }
                    if(ans.equals("default"))
                        ans="No";
                }
                else if(words[3].equals("human")){
                    for(int i=0; i<3; ++i){
                        if(category[humans][i].equals(words[1])){
                            ans="Yes";
                            break;
                        }
                    }
                    if(ans.equals("default"))
                        ans="No";
                }
                else if(words[3].equals("car")){
                    for(int i=0; i<3; ++i){
                        if(category[cars][i].equals(words[1])){
                            ans="Yes";
                            break;
                        }
                    }
                    if(ans.equals("default"))
                        ans="No";
                }
                else if(words[3].equals("cat")){
                    for(int i=0; i<3; ++i){
                        if(category[cats][i].equals(words[1])){
                            ans="Yes";
                            break;
                        }
                    }
                    if(ans.equals("default"))
                        ans="No";
                }
                else if(words[3].equals("dog")){
                    for(int i=0; i<3; ++i){
                        if(category[dogs][i].equals(words[1])){
                            ans="Yes";
                            break;
                        }
                    }
                    if(ans.equals("default"))
                        ans="No";
                }
                else{
                    good=false;
                }

                if(!(words[2].equals("a")) || !good ){
                    ans="I can't explain this question";
                }
            }

            ///////////////////////////////////////Another sort of questions////////////////////////////////////
            else if(words[0].equals("can")){
                for(int i=0; i<5; ++i){
                    for(int k=0; k<3; ++k){
                        if(category[i][k].equals(words[1])){
                            switch(i){
                                case 0:
                                    object1="mobile";
                                    break;
                                case 1:
                                    object1="human";
                                    break;
                                case 2:
                                    object1="car";
                                    break;
                                case 3:
                                    object1="cat";
                                    break;
                                default:
                                    object1="dog";
                            }
                        }
                        if(category[i][k].equals(words[4])){
                            switch(i){
                                case 0:
                                    object2="mobile";
                                    break;
                                case 1:
                                    object2="human";
                                    break;
                                case 2:
                                    object2="car";
                                    break;
                                case 3:
                                    object2="cat";
                                    break;
                                default:
                                    object2="dog";
                            }
                        }
                    }
                }
                if(words[4].equals("me"))
                    object2="human";

                if( object1.equals("default")|| object2.equals("default") ){
                    ans="No";
                }
                else if( (object1.equals("human")&&object2.equals("human")) ||
                         (object1.equals("dog")&&object2.equals("dog"))     ||
                         (object1.equals("cat")&&object2.equals("cat"))     ||
                         (object1.equals("human")&&object2.equals("cat"))   ||
                         (object1.equals("cat")&&object2.equals("human"))   ||
                         (object1.equals("human")&&object2.equals("dog"))   ||
                         (object1.equals("dog")&&object2.equals("human"))   ){
                    ans="Yes";
                }
                else{
                    ans="No";
                }

                if( (!(words[2].equals("play"))&&!(words[2].equals("plays"))) || !(words[3].equals("with"))){
                    ans="I can't explain this question";
                }
            }

            ///////////////////////////////////A sentence////////////////////////////
            else{
                if(words[3].equals("mobile")){
                    for(int i=0; i<3; ++i){
                        if(category[mobiles][i].equals(words[0])){
                            ans="Right Sentence";
                            break;
                        }
                    }
                    if(ans.equals("default"))
                        ans="Wrong Sentence";
                }
                else if(words[3].equals("human")){
                    for(int i=0; i<3; ++i){
                        if(category[humans][i].equals(words[0])){
                            ans="Right Sentence";
                            break;
                        }
                    }
                    if(ans.equals("default"))
                        ans="Wrong Sentence";
                }
                else if(words[3].equals("car")){
                    for(int i=0; i<3; ++i){
                        if(category[cars][i].equals(words[0])){
                            ans="Right Sentence";
                            break;
                        }
                    }
                    if(ans.equals("default"))
                        ans="Wrong Sentence";
                }
                else if(words[3].equals("cat")){
                    for(int i=0; i<3; ++i){
                        if(category[cats][i].equals(words[0])){
                            ans="Right Sentence";
                            break;
                        }
                    }
                    if(ans.equals("default"))
                        ans="Wrong Sentence";
                }
                else if(words[3].equals("dog")){
                    for(int i=0; i<3; ++i){
                        if(category[dogs][i].equals(words[0])){
                            ans="Right Sentence";
                            break;
                        }
                    }
                    if(ans.equals("default"))
                        ans="Wrong Sentence";
                }
                else{
                    good=false;
                }

                if( (!(words[1].equals("is"))&&!(words[1].equals("am"))) || !(words[2].equals("a")) || !good )
                    ans="I can't explain this question";
            }
        }
        
        //////////////////////////////Print the answer///////////////////////////
        answer1 = (TextField) (ob[0]);

        try {
            answer1.setText(ans);
        } catch (Exception e) {
            e.printStackTrace();
        }

        
        //////////////////////////////Send the answer///////////////////////////
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.addReceiver(new AID("explain", AID.ISLOCALNAME));

        msg.setContent(ans);
        send(msg);

        doDelete();

    }

    @Override
    protected void takeDown() {
    }

}
