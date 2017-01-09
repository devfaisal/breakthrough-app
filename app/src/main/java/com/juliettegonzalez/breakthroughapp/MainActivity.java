package com.juliettegonzalez.breakthroughapp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private LinearLayout expand_start_layout;
    private LinearLayout expand_hidden_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Typeface caviarDreams_bold = Typeface.createFromAsset(getAssets(), "fonts/CaviarDreams_Bold.ttf");
        ((TextView)findViewById(R.id.title_text_view)).setTypeface(caviarDreams_bold);

        getSupportActionBar().hide();


        expand_start_layout = (LinearLayout) findViewById(R.id.expand_start_layout);
        expand_hidden_content = (LinearLayout) findViewById(R.id.expand_hidden_content);


        Button newGameBtn = (Button) findViewById(R.id.new_game_btn);
        newGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTransition();
            }
        });

        Button rulesBtn = (Button) findViewById(R.id.rules_btn);
        rulesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RulesActivity.class);
                startActivity(intent);
            }
        });

        TextView creditLink = (TextView) findViewById(R.id.credits_link);
        creditLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreditsActivity.class);
                startActivity(intent);

                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            }
        });

    }


    private void startTransition(){
        expand_hidden_content.animate()
                .alpha(0)
                .setDuration(200)
                .setInterpolator(new AccelerateInterpolator())
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        expand_start_layout.animate()
                                .scaleY(5)
                                .scaleX((float)1.5)
                                .setInterpolator(new AccelerateDecelerateInterpolator())
                                .setDuration(400)
                                .setListener(new AnimatorListenerAdapter() {
                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        Intent intent = new Intent(MainActivity.this, GameActivity.class);
                                        startActivity(intent);
                                        overridePendingTransition(0,0);
                                    }
                                })
                                .start();
                    }
                })
                .start();
    }
}
