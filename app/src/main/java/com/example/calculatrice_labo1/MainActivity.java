package com.example.calculatrice_labo1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // Declaration des differentes variables
    private TextView Screen;
    private String input="", Answer;
    private boolean clearResult;
    private Button AC,CE,C,Zero,Un,Deux,Trois,Quatre,Cinq,Carre,SurX,PlusMoins,
            Six,Sept,Huit,Neuf,Multi,Soust, Div,Percent, Plus, Equal;

    // Associer les variables aux boutons
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Screen=findViewById(R.id.screen);
        AC=findViewById(R.id.ac);
        CE=findViewById(R.id.ce);
        C=findViewById(R.id.c);
        PlusMoins=findViewById(R.id.plusmoins);
        Carre=findViewById(R.id.carre);
        SurX=findViewById(R.id.surX);
        Div=findViewById(R.id.div);
        Plus=findViewById(R.id.plus);
        Soust=findViewById(R.id.soust);
        Equal=findViewById(R.id.equal);
        Percent=findViewById(R.id.percent);
        Zero=findViewById(R.id.zero);
        Un=findViewById(R.id.un);
        Deux=findViewById(R.id.deux);
        Trois=findViewById(R.id.trois);
        Quatre=findViewById(R.id.quatre);
        Cinq=findViewById(R.id.cinq);
        Six=findViewById(R.id.six);
        Sept=findViewById(R.id.sept);
        Huit=findViewById(R.id.huit);
        Neuf=findViewById(R.id.neuf);
        Multi=findViewById(R.id.multi);
    }

    // Affichage des boutons et definitions de leur role pour les diverses operations

    public void ButtonClick(View view){
        Button button = (Button) view;
        String data=button.getText().toString();
        switch (data){
            case "⬅":
                if(input.length()>0){
                    clearResult=false;
                    String newText=input.substring(0,input.length()-1);
                    input=newText;
                }
                break;
            case "CE":
                input="";
                break;
            case "C":
                String newText=input.substring(0,input.length()-input.length());
                input=newText;
                break;
            case "+":
                Solve();
                input+="+";
                break;
            case "x":
                Solve();
                input+="*";
                break;
            case "=":
                Solve();
                Answer=input;
                break;
            case "√":
                if (Double.parseDouble(input) > 0) {
                    newText = String.format ("%.2f",Math.sqrt(Double.parseDouble(input)));
                    input=newText;
                }
                break;
            case "+-":
                if( Double.parseDouble(input) > 0){
                    newText= "-"+ input;
                    input=newText;
                }
                else input=input.replace("-","");

                break;
            case "1/x":
                if (Double.parseDouble(input) > 0) {
                    newText = String.format ("%.2f",(1/Double.parseDouble(input)));
                    input=newText;
                }
                break;
            case "%":
                if (Double.parseDouble(input) > 0) {
                    newText = String.format ("%.2f",(Double.parseDouble(input)/100));
                    input=newText;
                }
                break;
            default:
                if(input==null){
                    input="";
                }
                if(data.equals("+")||data.equals("-")||data.equals("/")){
                    Solve();
                }
                else if(clearResult==true){
                    input="";
                    clearResult=false;
                }
                input+=data;


        }
        Screen.setText(input);
    }

    // La resolution des operations
    private void Solve(){
        if(input.split("\\*").length==2){
            String number[]=input.split("\\*");
            try{
                double multi = Double.parseDouble(number[0])*Double.parseDouble(number[1]);
                input = multi+"";
            }
            catch (Exception e){
        }

    }
        else if(input.split("/").length==2){
            String numbers[]=input.split("/");
            try{
                double div=Double.parseDouble(numbers[0])/Double.parseDouble(numbers[1]);
                input=div+"";
            }
            catch (Exception e){
                //Display error
            }
    }
        else if(input.split("\\+").length==2){
            String numbers[]=input.split("\\+");
            try{
                double plus=Double.parseDouble(numbers[0])+Double.parseDouble(numbers[1]);
                input=plus+"";
            }
            catch (Exception e){
                //Display error
            }
        }

        else if(input.split("\\-").length>1){
            String numbers[]=input.split("\\-");
            if(numbers[0]=="" && numbers.length==2){
                numbers[0]=0+"";
            }
            try{
                double soust=0;
                if(numbers.length==2) {
                    soust = Double.parseDouble(numbers[0]) - Double.parseDouble(numbers[1]);
                }
                else if(numbers.length==3){
                    soust = -Double.parseDouble(numbers[1]) - Double.parseDouble(numbers[2]);
                }
                input=soust+"";
            }
            catch (Exception e){
                //Display error
            }
        }


        String n[]=input.split("\\.");
        if(n.length>1){
            if(n[1].equals("0")){
                input=n[0];
            }
        }
        Screen.setText(input);
    }
}
