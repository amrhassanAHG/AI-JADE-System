package projectai;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.wrapper.StaleProxyException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static projectai.Explain.explain1;
import static projectai.Answer.*;
import static projectai.ProjectAI.*;

public class ExplainBehaviour extends CyclicBehaviour {
    
    @Override
    public void action() {
        String exp="";
        
        ACLMessage msg = getAgent().receive();
        if (msg != null) {

            String ans = msg.getContent();
            if(ans.equals("I can't explain this question")){
                exp="May be you asked a wrong question";
            }
            
            ////////////////////////////////////////////Explanation for right answer//////////////////////////////////////
            else if(ans.equals("Right Sentence")){
                if(words[3].equals("mobile")){
                    exp = "Because " + category[mobiles][0] + " , " + category[mobiles][1] + " and " + category[mobiles][2] + " are mobile phones.";
                }
                else if(words[3].equals("human")){
                    exp = "Because " + category[humans][0] + " , " + category[humans][1] + " and you are humans.";
                }
                else if(words[3].equals("car")){
                    exp = "Because " + category[cars][0] + " , " + category[cars][1] + " and " + category[cars][2] + " are cars.";
                }
                else if(words[3].equals("cat")){
                    exp = "Because " + category[cats][0] + " , " + category[cats][1] + " and " + category[cats][2] + " are cats.";
                }
                else if(words[3].equals("dog")){
                    exp = "Because " + category[dogs][0] + " , " + category[dogs][1] + " and " + category[dogs][2] + " are dogs.";
                }
            }
            
            ////////////////////////////////////////////Explanation for yes//////////////////////////////////////
            else if(ans.equals("Yes")){
                if(words[0].equals("is") || words[0].equals("am")){
                    if(words[3].equals("mobile")){
                        exp = category[mobiles][0] + " , " + category[mobiles][1] + " and " + category[mobiles][2] + " are mobile phones.";
                    }
                    else if(words[3].equals("human")){
                        exp = category[humans][0] + " , " + category[humans][1] + " and you are humans.";
                    }
                    else if(words[3].equals("car")){
                        exp = category[cars][0] + " , " + category[cars][1] + " and " + category[cars][2] + " are cars.";
                    }
                    else if(words[3].equals("cat")){
                        exp = category[cats][0] + " , " + category[cats][1] + " and " + category[cats][2] + " are cats.";
                    }
                    else if(words[3].equals("dog")){
                        exp = category[dogs][0] + " , " + category[dogs][1] + " and " + category[dogs][2] + " are dogs.";
                    }
                }
                
                else{
                    if( object1.equals("human") && object2.equals("human") )
                        exp = "Humans can play with each others";
                    else if( object1.equals("dog") && object2.equals("dog") )
                        exp = "Dogs can play with each others";
                    else if( object1.equals("cat") && object2.equals("cat") )
                        exp = "Cats can play with each others";
                    else if( object1.equals("human") && object2.equals("cat") )
                        exp = "Humans can play with cats";
                    else if( object1.equals("cat") && object2.equals("human") )
                        exp = "Cats can play with humans";
                    else if( object1.equals("human") && object2.equals("dog") )
                        exp = "Humans can play with dogs";
                    else if( object1.equals("dog") && object2.equals("human") )
                        exp = "dogs can play with humans";
                }
            }
            
            ////////////////////////////////////////////Explanation for wrong answer//////////////////////////////////////
            else if(ans.equals("Wrong Sentence")){
                boolean found=false;
                int catg=0;
                String word1="", word2="";
                for(int i=0; i<5; ++i){
                    for(int k=0; k<3; ++k){
                        if(category[i][k].equals(words[0]) && !found){
                            switch(i){
                                case 0:
                                    word1="mobile phone";
                                    break;
                                case 1:
                                    word1="human";
                                    break;
                                case 2:
                                    word1="car";
                                    break;
                                case 3:
                                    word1="cat";
                                    break;
                                default:
                                    word1="dog";
                            }
                            found=true;
                        }
                    }
                }
                
                switch(words[3]){
                    case "mobile":
                        catg=0;
                        word2="mobile phones";
                        break;
                    case "human":
                        catg=1;
                        word2="humans";
                        break;
                    case "car":
                        catg=2;
                        word2="cars";
                        break;
                    case "cat":
                        catg=3;
                        word2="cats";
                        break;
                    default:
                        catg=4;
                        word2="dogs";
                }
                
                if(!found){
                    exp = words[0] + " does'nt belong to any category.";
                }
                else{
                    if(words[0].equals("i")){
                        exp = "You are a human, but " + category[catg][0] + " , " + category[catg][1] + " and " + category[catg][2] + " are " + word2;
                    }
                    else{
                        exp = words[0] + " is a " + word1 + ", but " + category[catg][0] + " , " + category[catg][1] + " and " + (word2.equals("humans")? "you" : category[catg][2]) + " are " + word2;
                    }
                }
            }
            
            ////////////////////////////////////////////Explanation for no//////////////////////////////////////
            else if(ans.equals("No")){
                if(words[0].equals("is")||words[0].equals("am")){
                    boolean found=false;
                    int catg=0;
                    String word1="", word2="";
                    for(int i=0; i<5; ++i){
                       for(int k=0; k<3; ++k){
                           if(category[i][k].equals(words[1]) && !found){
                               switch(i){
                                   case 0:
                                       word1="mobile phone";
                                       break;
                                   case 1:
                                       word1="human";
                                       break;
                                   case 2:
                                       word1="car";
                                       break;
                                   case 3:
                                       word1="cat";
                                       break;
                                   default:
                                       word1="dog";
                               }
                               found=true;
                           }
                       }
                   }

                   switch(words[3]){
                       case "mobile":
                           catg=0;
                           word2="Mobile phones";
                           break;
                       case "human":
                           catg=1;
                           word2="Humans";
                           break;
                       case "car":
                           catg=2;
                           word2="Cars";
                           break;
                       case "cat":
                           catg=3;
                           word2="Cats";
                           break;
                       default:
                           catg=4;
                           word2="Dogs";
                   }

                   if(!found){
                       exp = words[1] + " does'nt belong to any category.";
                   }
                   else{
                       if(words[1].equals("i")){
                           exp = "You are a human. " + word2 + " are " + category[catg][0] + " , " + category[catg][1] + " and " + category[catg][2];
                       }
                       else{
                           exp = words[1] + " is a " + word1 + ". " + word2 + " are " + category[catg][0] + " , " + category[catg][1] + " and " + (word2.equals("humans")? "you" : category[catg][2]);
                       }
                   }
                }
                
                else{
                    if( object1.equals("default") && object2.equals("default") )
                        exp = words[1] + " and " + words[4] + " don't belong to any category.";
                    else if( object1.equals("default") && !(object2.equals("default")) )
                        exp = words[1] + " doesn't belong to any category.";
                    else if( !(object1.equals("default")) && object2.equals("default") )
                        exp = words[4] + " doesn't belong to any category.";
                    
                    else{
                        exp = (words[1].equals("i")? "You":words[1]) + " can't play with " + (words[4].equals("me")? "you":words[4]) + ". ";
                        if( (object1.equals("mobile")||object1.equals("car")) && (object2.equals("mobile")||object2.equals("car")) )
                            exp += " and they can't play with any other objects";
                        else{
                            if(object1.equals("mobile")||object1.equals("car")){
                                if(object2.equals("human")){
                                    exp += category[cats][0] + " , " + category[cats][1] + " , " + category[cats][2] + " , " + category[dogs][0] + " , " + category[dogs][1] + " and " + category[dogs][2] + " can play with " + (words[4].equals("me")? "you":words[4]);
                                }
                                else{
                                    exp += category[humans][0] + " , " + category[humans][1] + " and you can play with " + words[4];
                                }
                            }
                            else{
                                if(object1.equals("human")){
                                    exp += "You can paly with " + category[cats][0] + " , " + category[cats][1] + " , " + category[cats][2] + " , " + category[dogs][0] + " , " + category[dogs][1] + " and " + category[dogs][2];
                                }
                                else{
                                    exp += words[1] + " can play with " + category[humans][0] + " , " + category[humans][1] + " and you";
                                }
                            }
                        }
                    }
                }
            }

            ////////////////////////////////////////////Print the explanation//////////////////////////////////////
            explain1.setText(exp);
            
            try {
                ProjectAI.rma[0].kill();
            } catch (StaleProxyException ex) {
                Logger.getLogger(ExplainBehaviour.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            block();
        }

    }
}
