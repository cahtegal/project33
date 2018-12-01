package com.nextwin.suling;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Suling extends AppCompatActivity {

    ImageView imgSuling,imgDo,imgRe,imgMi,imgFa,imgSol,imgLa,imgSi,imgDoo, imgBack;
    ImageView soundDo,soundRe,soundMi,soundFa,soundSol,soundLa,soundSi,soundDoo;
    RelativeLayout layUtama;
    Button btnSound;
    private int ss1;
    private int ss2;
    private int ss3;
    private int ss4;
    private int ss5;
    private int ss6;
    private int ss7;
    private int ss8;
    private int sss1;
    private int sss2;
    private int sss3;
    private int sss4;
    private int sss5;
    private int sss6;
    private int sss7;
    private int sss8;
    private SoundPool sp;
    boolean isSound1 = true;

    private AdView mAdView,mAdView2;
    private InterstitialAd mInterstitialAd;
    final AnimatorSet animatorSet = new AnimatorSet();
    MediaPlayer mpSound1 = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suling);

        deklarasi();
        action();
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener() {

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
            }
        });
        AdRequest adRequest2 = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView2.loadAd(adRequest2);
        mAdView2.setAdListener(new AdListener() {

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
            }
        });
//        startAnimationSound();
    }

    @Override
    public void onBackPressed() {
        mpSound1.start();
        Menu.isUtama = false;
        iklan();
        finish();
    }

    @Override
    public void onPause() {
        // This method should be called in the parent Activity's onPause() method.
        if (mAdView != null) {
            mAdView.pause();
        }
        if (mAdView2 != null) {
            mAdView2.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        // This method should be called in the parent Activity's onResume() method.
        if (mAdView != null) {
            mAdView.resume();
        }
        if (mAdView2 != null) {
            mAdView2.resume();
        }

        if (Menu.tema == 0) {
            layUtama.setBackground(getResources().getDrawable(R.drawable.bg1));
        } else if (Menu.tema == 1) {
            layUtama.setBackground(getResources().getDrawable(R.drawable.bg2));
        } else if (Menu.tema == 2) {
            layUtama.setBackground(getResources().getDrawable(R.drawable.bg3));
        } else if (Menu.tema == 3) {
            layUtama.setBackground(getResources().getDrawable(R.drawable.bg4));
        } else if (Menu.tema == 4) {
            layUtama.setBackground(getResources().getDrawable(R.drawable.bg5));
        }

    }

    @Override
    public void onDestroy() {
        // This method should be called in the parent Activity's onDestroy() method.
        if (mAdView != null) {
            mAdView.destroy();
        }
        if (mAdView2 != null) {
            mAdView2.destroy();
        }
        super.onDestroy();
    }

    private void deklarasi() {
        mpSound1 = MediaPlayer.create(this,R.raw.tok);
        imgBack = findViewById(R.id.imgBack);
        layUtama = findViewById(R.id.layUtama);
        mAdView = findViewById(R.id.adView);
        mAdView2 = findViewById(R.id.adView2);
        imgSuling = findViewById(R.id.imgSuling);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imgSuling.setImageResource(R.drawable.sulingbambu);
            }
        },400);
        imgDo = findViewById(R.id.imgDo);
        imgRe = findViewById(R.id.imgRe);
        imgMi = findViewById(R.id.imgMi);
        imgFa = findViewById(R.id.imgFa);
        imgSol = findViewById(R.id.imgSol);
        imgLa = findViewById(R.id.imgLa);
        imgSi = findViewById(R.id.imgSi);
        imgDoo = findViewById(R.id.imgDoo);
        soundDo = findViewById(R.id.soundDo);
        soundRe = findViewById(R.id.soundRe);
        soundMi = findViewById(R.id.soundMi);
        soundFa = findViewById(R.id.soundFa);
        soundSol = findViewById(R.id.soundSol);
        soundLa = findViewById(R.id.soundLa);
        soundSi = findViewById(R.id.soundSi);
        soundDoo = findViewById(R.id.soundDoo);
        btnSound = findViewById(R.id.btnSound);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            sp = new SoundPool.Builder().setMaxStreams(5).build();
        } else {
            sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ss1 = sp.load(Suling.this, R.raw.s_1, 1);
                ss2 = sp.load(Suling.this, R.raw.s_2, 1);
                ss3 = sp.load(Suling.this, R.raw.s_3, 1);
                ss4 = sp.load(Suling.this, R.raw.s_4, 1);
            }
        },200);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ss5 = sp.load(Suling.this, R.raw.s_5, 1);
                ss6 = sp.load(Suling.this, R.raw.s_6, 1);
                ss7 = sp.load(Suling.this, R.raw.s_7, 1);
                ss8 = sp.load(Suling.this, R.raw.s_8, 1);
            }
        },400);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                sss1 = sp.load(Suling.this, R.raw.flute_do, 1);
                sss2 = sp.load(Suling.this, R.raw.flute_re, 1);
                sss3 = sp.load(Suling.this, R.raw.flute_mi, 1);
                sss4 = sp.load(Suling.this, R.raw.flute_fa, 1);
            }
        },600);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                sss5 = sp.load(Suling.this, R.raw.flute_sol, 1);
                sss6 = sp.load(Suling.this, R.raw.flute_la, 1);
                sss7 = sp.load(Suling.this, R.raw.flute_si, 1);
                sss8 = sp.load(Suling.this, R.raw.flute_du, 1);
            }
        },800);
    }
    
    private void action() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        imgDo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (isSound1) {
                    return playSound(ss1, motionEvent, imgDo);
                } else {
                    return playSound(sss1, motionEvent, imgDo);
                }
            }
        });

        imgRe.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (isSound1) {
                    return playSound(ss2,motionEvent,imgRe);
                } else {
                    return playSound(sss2,motionEvent,imgRe);
                }
            }
        });

        imgMi.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (isSound1) {
                    return playSound(ss3,motionEvent,imgMi);
                } else {
                    return playSound(sss3,motionEvent,imgMi);
                }
            }
        });

        imgFa.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (isSound1) {
                    return playSound(ss4,motionEvent,imgFa);
                } else {
                    return playSound(sss4,motionEvent,imgFa);
                }
            }
        });

        imgSol.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (isSound1) {
                    return playSound(ss5,motionEvent,imgSol);
                } else {
                    return playSound(sss5,motionEvent,imgSol);
                }
            }
        });

        imgLa.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (isSound1) {
                    return playSound(ss6,motionEvent,imgLa);
                } else {
                    return playSound(sss6,motionEvent,imgLa);
                }
            }
        });

        imgSi.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (isSound1) {
                    return playSound(ss7,motionEvent,imgSi);
                } else {
                    return playSound(sss7,motionEvent,imgSi);
                }
            }
        });

        imgDoo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (isSound1) {
                    return playSound(ss8,motionEvent,imgDoo);
                } else {
                    return playSound(sss8,motionEvent,imgDoo);
                }
            }
        });

        btnSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isSound1) {
                    btnSound.setBackground(getResources().getDrawable(R.drawable.ic_sound2));
                    sp.play(sss1, 1.0f, 1.0f, 0, 0, 1.0f);
                    isSound1 = false;
                } else {
                    btnSound.setBackground(getResources().getDrawable(R.drawable.ic_sound1));
                    sp.play(ss1, 1.0f, 1.0f, 0, 0, 1.0f);
                    isSound1 = true;
                }
            }
        });
        startAnimationSound();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("isBottomFlute");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                if (value != null) {
                    if (value.equals("0")) {
                        mAdView.setVisibility(View.VISIBLE);
                        mAdView2.setVisibility(View.GONE);
                    } else {
                        mAdView2.setVisibility(View.VISIBLE);
                        mAdView.setVisibility(View.GONE);
                    }
                } else {
                    mAdView.setVisibility(View.VISIBLE);
                    mAdView2.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                mAdView.setVisibility(View.VISIBLE);
                mAdView2.setVisibility(View.GONE);
            }
        });

    }

    private boolean playSound(int id, MotionEvent motionEvent,ImageView imgSound) {
        Log.d("Motionsss  ",String.valueOf(motionEvent));
        if (motionEvent == null || motionEvent.getAction() == 0) {
            imgSound.setImageDrawable(getResources().getDrawable(R.drawable.btn_pressed));
            sp.play(id, 1.0f, 1.0f, 0, 0, 1.0f);
            if (id == 1 || id == 9) {
                soundDo.setVisibility(View.VISIBLE);
            } else if (id == 2 || id == 10) {
                soundRe.setVisibility(View.VISIBLE);
            } else if (id == 3 || id == 11) {
                soundMi.setVisibility(View.VISIBLE);
            } else if (id == 4 || id == 12) {
                soundFa.setVisibility(View.VISIBLE);
            } else if (id == 5 || id == 13) {
                soundSol.setVisibility(View.VISIBLE);
            } else if (id == 6 || id == 14) {
                soundLa.setVisibility(View.VISIBLE);
            } else if (id == 7 || id == 15) {
                soundSi.setVisibility(View.VISIBLE);
            } else if (id == 8 || id == 16) {
                soundDoo.setVisibility(View.VISIBLE);
            }
        } else if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
            imgSound.setImageDrawable(getResources().getDrawable(R.drawable.btn_press));
            sp.autoPause();
            if (id == 1 || id == 9) {
                soundDo.setVisibility(View.INVISIBLE);
            } else if (id == 2 || id == 10) {
                soundRe.setVisibility(View.INVISIBLE);
            } else if (id == 3 || id == 11) {
                soundMi.setVisibility(View.INVISIBLE);
            } else if (id == 4 || id == 12) {
                soundFa.setVisibility(View.INVISIBLE);
            } else if (id == 5 || id == 13) {
                soundSol.setVisibility(View.INVISIBLE);
            } else if (id == 6 || id == 14) {
                soundLa.setVisibility(View.INVISIBLE);
            } else if (id == 7 || id == 15) {
                soundSi.setVisibility(View.INVISIBLE);
            } else if (id == 8 || id == 16) {
                soundDoo.setVisibility(View.INVISIBLE);
            }
        }
        return true;
    }

    private void iklan() {
        mInterstitialAd = new InterstitialAd(Suling.this);
        mInterstitialAd.setAdUnitId("ca-app-pub-5730449577374867/3996649286");
        mInterstitialAd.setAdListener(new AdListener() {

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                if(mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
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
    }

    private void startAnimationSound() {
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(btnSound, "scaleY", 0.5f);
        scaleY.setDuration(200);
        ObjectAnimator scaleYBack = ObjectAnimator.ofFloat(btnSound, "scaleY", 1f);
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
        btnSound.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        animatorSet.start();
    }

}
