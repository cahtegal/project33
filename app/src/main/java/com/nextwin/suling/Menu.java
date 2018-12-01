package com.nextwin.suling;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class Menu extends AppCompatActivity {
    ImageView imgPlay,imgShare,imgRateUs, imgTema;
    InterstitialAd mInterstitialAd;
    final AnimatorSet animatorSet = new AnimatorSet();
    public static boolean isUtama = false, iklanOpen = false;
    public static int tema = 0;
    MediaPlayer mpSound1 = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        deklarasi();
        action();
        startAnimationPlay();
    }

    private void deklarasi() {
        imgPlay = findViewById(R.id.imgPlay);
        imgShare = findViewById(R.id.imgShare);
        imgRateUs = findViewById(R.id.btnRateUs);
        imgTema = findViewById(R.id.btnTema);
        mpSound1 = MediaPlayer.create(this,R.raw.tok);
    }

    @Override
    protected void onResume() {
        super.onResume();
        iklanOpen = false;
    }

    private void action() {
        imgShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mpSound1.start();
                Intent sendIntent;
                sendIntent = new Intent(android.content.Intent.ACTION_SEND);
                sendIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Play this Real Flute / Real Suling Bus Tayo for fun\n\nhttps://play.google.com/store/apps/details?id="+BuildConfig.APPLICATION_ID);
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, "Share with"));
            }
        });

        imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mpSound1.start();
                iklan();
            }
        });

        imgRateUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mpSound1.start();
                String appPackageName = BuildConfig.APPLICATION_ID; // getPackageName() from Context or Activity object
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }
            }
        });
        imgTema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mpSound1.start();
                startActivity(new Intent(Menu.this,TemaPoni.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        mpSound1.start();
        dialogOut();
    }

    private void dialogOut() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Menu.this);
        alertDialogBuilder.setTitle("Quit");
        alertDialogBuilder
                .setMessage("Quit from the game now ?")
                .setCancelable(false)
                .setPositiveButton("Yeah", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        finish();
                    }
                })
                .setNegativeButton("No, I like this game", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void iklan() {
        final ProgressDialog pdialog = new ProgressDialog(Menu.this);
        pdialog.setMessage("Please wait ...");
        pdialog.show();
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-5730449577374867/3996649286");
        mInterstitialAd.setAdListener(new AdListener() {

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                if(mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                    pdialog.dismiss();
                    if (!iklanOpen) {
                        iklanOpen = true;
                    }
                }
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
                if (!isUtama) {
                    startActivity(new Intent(Menu.this,Suling.class));
                    isUtama = true;
                }
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
            }
        });

        AdRequest adRequest = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequest);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!iklanOpen) {
                    pdialog.dismiss();
                    iklanOpen = true;
                    if (!isUtama) {
                        startActivity(new Intent(Menu.this, Suling.class));
                        isUtama = true;
                    }
                }
            }
        },4000);
    }
    private void startAnimationPlay() {
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(imgPlay, "scaleX", 0.5f);
        scaleY.setDuration(200);
        ObjectAnimator scaleYBack = ObjectAnimator.ofFloat(imgPlay, "scaleX", 1f);
        scaleYBack.setDuration(500);
        scaleYBack.setInterpolator(new BounceInterpolator());
        animatorSet.setStartDelay(600);
        animatorSet.playSequentially(scaleY, scaleYBack);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                animatorSet.setStartDelay(500);
                animatorSet.start();
            }
        });
        imgPlay.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        animatorSet.start();
    }
}
