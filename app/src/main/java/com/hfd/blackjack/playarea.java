package com.hfd.blackjack;

import static java.util.logging.Logger.global;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Random;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class playarea extends AppCompatActivity {

    more m = new more();

    Button yes;
    Button no;

    public String cards_user = "";
    public String cards_computer = "";

    int[] computer_cards = {};
    int[] user_cards = {};
    int user_score;
    int computer_score;

    TextView computer;
    TextView usert;
    TextView user_score_text;
    TextView computer_score_text;
    TextView doyou;
    TextView again;


    //function to append element in array.
    public static int[] append(int[] arr, int element) {
        final int N = arr.length;
        arr = Arrays.copyOf(arr, N + 1);
        arr[N] = element;
        return arr;
    }

    //function to deal a card.
    public static int deal_cards() {
        long cards[] = {11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 10};
        Random ran = new Random();
        int index = ran.nextInt(cards.length);
        int card = (int) cards[index];
        return card;
    }

    //main function
    public void user() {

        for (int i = 0; i < 2; i++) {
            int card = deal_cards();
            this.user_cards = append(this.user_cards, card);
        }
    }
    public String ucard(){
        this.cards_user = "";
        //dealing with user cards.
        for (int u=0; u<this.user_cards.length;u++){
            this.cards_user = this.cards_user + " " + this.user_cards[u];
        }
        //String final_cards_user = cards_user.replaceAll(" ", ", ");
        return this.cards_user;
    }

    public int computer() {
        for (int i = 0; i < 2; i++) {
            int card2 = deal_cards();
            this.computer_cards = append(this.computer_cards, card2);
        }
        return this.computer_cards[0];
    }
    public String ccard(){
        this.cards_computer = "";
        //dealing with computer cards.
        this.computer_score = m.score(this.computer_cards);
        while (this.computer_score<17) {
            int card = deal_cards();
            this.computer_cards = append(this.computer_cards, card);
            this.computer_score = m.score(this.computer_cards);
        }
        for (int c=0; c<this.computer_cards.length;c++){
            this.cards_computer = this.cards_computer + " " + this.computer_cards[c];
        }
        return this.cards_computer;
    }


    public void user_new_card(){
        int card = deal_cards();
        this.user_cards = append(this.user_cards, card);
    }

    public int user_score(){
       return this.user_score = m.score(this.user_cards);
    }
    public int computer_score(){
        return this.computer_score = m.score(this.computer_cards);
    }

    public void go(){
        if (this.user_score>21 ||  this.computer_score>21 || (this.computer_score==21 && this.computer_cards.length==2) || (this.user_score==21 && this.user_cards.length==2)){
            again.setVisibility(View.VISIBLE);
            vanish();
            end();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playarea);

        computer = (TextView) findViewById(R.id.computer_cards);
        usert = (TextView) findViewById(R.id.user_cards);
        doyou = (TextView) findViewById(R.id.dou);
        computer_score_text = (TextView) findViewById(R.id.computer_score);
        user_score_text = (TextView) findViewById(R.id.user_score);
        again = (TextView) findViewById(R.id.again);
        again.setVisibility(View.INVISIBLE);
        user();
        usert.setText(ucard());
        computer.setText(String.valueOf(computer())+" ?");
        user_score_text.setText("Score: "+user_score());

        yes = (Button) findViewById(R.id.ycard);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_new_card();
                usert.setText(ucard());
                user_score_text.setText("Score: "+user_score());
                go();

            }
        });

//        this.computer_score = m.score(this.computer_cards);
//        this.user_score = m.score(this.user_cards);


        no = (Button) findViewById(R.id.ncard);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                end();
                vanish();
                again.setVisibility(View.VISIBLE);
            }
        });

        again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play_again();
            }
        });

    }
    public void vanish(){
        yes.setVisibility(View.INVISIBLE);
        no.setVisibility(View.INVISIBLE);
    }
    public void end(){
        this.computer.setText(ccard());
        this.computer_score_text.setText("Score: "+computer_score());

        if(computer_score()==user_score()){
            this.doyou.setText("Draw.");
        }
        else if(user_score()>21){
            this.doyou.setText("You Lost.");
        }
        else if(computer_score()>21){
            this.doyou.setText("You Won.");
        }
        else if(user_score()>computer_score()){
            this.doyou.setText("You Won!");
        }
        else if(user_score()<computer_score()){
            this.doyou.setText("You Lost.");
        }
        else{
            this.doyou.setText("IDK.");
        }
    }
    public void play_again(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}