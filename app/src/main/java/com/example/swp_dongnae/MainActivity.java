package com.example.swp_dongnae;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;

import java.util.ArrayList;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
//sdsadsadasd
public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private SignInButton btn_google; //구글 로그인 버튼
    private FirebaseAuth auth;  //파이어베이스 인증
    private GoogleApiClient googleApiClient; // 구글 클라이언트 API
    private static final int REQ_SIGN_GOOGLE = 100;// 구글 로그인 결과 코드

    private static final String TAG = "MainActivity";
    private View loginButton, logoutButton;
    private  ImageView profile;
    private TextView nickName;
    private ImageView profileImage;
    private Button BtnPopUp;
    private Button btn_next;
    private ImageView loginLogo;
    private Button test;//TODO 민규 전용 테스트 버튼

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQ_SIGN_GOOGLE) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                    if(result.isSuccess()){
                       GoogleSignInAccount account = result.getSignInAccount();
                        resultLogin(account);  //어카운트는 로그인 정보를 담고있음
                    }
        }
    }

    private void resultLogin(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(),null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this,"로그인 성공",Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(getApplicationContext(),ResultActivity.class);
                            intent.putExtra("nickName",account.getDisplayName());
                            intent.putExtra("photoUrl",String.valueOf(account.getPhotoUrl())); //특정 자료형을 스트링 형태로 변환

                            startActivity(intent);
                            btn_google.setVisibility(View.GONE);
                            logoutButton.setVisibility(View.VISIBLE);
                        }else{
                            Toast.makeText(MainActivity.this,"로그인 실패",Toast.LENGTH_SHORT).show();
                            btn_google.setVisibility(View.VISIBLE);
                            logoutButton.setVisibility(View.GONE);
                        }

                    }
                });
    }

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<CategorySub> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, Loading.class);
        startActivity(intent);



        loginButton = findViewById(R.id.login);
        logoutButton = findViewById(R.id.logout);
        profile = findViewById(R.id.profile);
        nickName = findViewById(R.id.nickname);
        profileImage = findViewById(R.id.profile);
        btn_next = findViewById(R.id.btn_next);
        loginLogo = findViewById(R.id.loginLogo);
        BtnPopUp = (Button)findViewById(R.id.btnPopUp); // 문의하기 버튼 추가

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SelectActivity.class);
                startActivity(intent);//액티비티 이동

            }
        });

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString((R.string.default_web_client_id)))
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,googleSignInOptions)
                .build();

        auth = FirebaseAuth.getInstance(); //파이어베이스 인증 객체 초기화
        btn_google = findViewById(R.id.btn_google);
        btn_google.setOnClickListener(new View.OnClickListener() {  // 구글 로그인 버튼을 누르면 작동
            @Override
            public void onClick(View view) {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent,REQ_SIGN_GOOGLE);
            }
        });



        Function2<OAuthToken, Throwable, Unit> callback = new Function2<OAuthToken, Throwable, Unit>() {
            @Override
            public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
                if (oAuthToken != null) {
                    //TBD
                }
                if (throwable != null) {
                    //TBD
                    Log.w(TAG, "invoke: " + throwable.getLocalizedMessage());
                }
                MainActivity.this.updateKakaoLoginUi();
                return null;
            }
        };

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(UserApiClient.getInstance().isKakaoTalkLoginAvailable(MainActivity.this)) {
                    UserApiClient.getInstance().loginWithKakaoTalk(MainActivity.this, callback);
                } else {
                    UserApiClient.getInstance().loginWithKakaoAccount(MainActivity.this, callback);
                }
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserApiClient.getInstance().logout(new Function1<Throwable, Unit>() {
                    @Override
                    public Unit invoke(Throwable throwable) {
                        updateKakaoLoginUi();
                        return null;
                    }
                });
            }
        });

        updateKakaoLoginUi();

        BtnPopUp.setOnClickListener(new View.OnClickListener() { //버튼 클릭시 문의하기 팝업 뜨는 내
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);
                final String[] copy = new String[1];
                String DevMail = "mgo8434kk@gmail.com";
                ad.setTitle("개발자 이메일");
                ad.setMessage(DevMail);

                ad.setNegativeButton("복사", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        copy[0] = DevMail;
                        CopyText("Dev", copy[0]);
                    }
                });

                ad.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                ad.show();
            }
        });
        test= (Button) findViewById(R.id.test);
        test.setOnClickListener(new View.OnClickListener() { //TODO 민규 전용 테스트 버튼
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewClub.class);
                intent.putExtra("bdh","bdh");
                startActivity(intent);//액티비티 이동
            }
        });

    }



    private void updateKakaoLoginUi() {
        UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
            @Override
            public Unit invoke(User user, Throwable throwable) {
                if (user != null) {

                    Log.i(TAG,"invoke: id="+user.getId());
                    Log.i(TAG,"invoke: email="+user.getKakaoAccount().getEmail());
                    Log.i(TAG,"invoke: nickName="+user.getKakaoAccount().getProfile().getNickname());
                    Log.i(TAG,"invoke: gender="+user.getKakaoAccount().getGender());
                    Log.i(TAG,"invoke: age="+user.getKakaoAccount().getAgeRange());

                    nickName.setText(user.getKakaoAccount().getProfile().getNickname());
                    Glide.with(profileImage).load(user.getKakaoAccount().getProfile().getThumbnailImageUrl()).circleCrop().into(profileImage);


                    loginButton.setVisibility(View.GONE);
                    logoutButton.setVisibility(View.VISIBLE);
                    btn_google.setVisibility(View.GONE);
                    BtnPopUp.setVisibility(View.GONE);
                    test.setVisibility(View.GONE);
                    btn_next.setVisibility(View.VISIBLE);
                    loginLogo.setVisibility(View.GONE);
                    profile.setVisibility(View.VISIBLE);

                } else {
                    nickName.setText(null);
                    profileImage.setImageBitmap(null);
                    loginButton.setVisibility((View.VISIBLE));
                    logoutButton.setVisibility(View.GONE);
                    btn_google.setVisibility(View.VISIBLE);
                    BtnPopUp.setVisibility(View.VISIBLE);
                    test.setVisibility(View.VISIBLE);
                    btn_next.setVisibility(View.GONE);
                    loginLogo.setVisibility(View.VISIBLE);
                    profile.setVisibility(View.GONE);
                }
                return null;
            }
        });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public void CopyText(String label, String textString){ // 클립보드에 붙여넣 TODO clipboard
        ClipboardManager clipboardManager; // 클립보드 객체 생성
        clipboardManager = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE); // 클립보드 객체 초기화

        ClipData clipData = ClipData.newPlainText(label, textString); // 클립데이터 객체 생성 및 객체 데이터 초기화

        clipboardManager.setPrimaryClip(clipData); // 객체 데이터를 객체 보드에 추가

        Toast.makeText(getApplicationContext(),"클립보드에 복사되었습니다.",Toast.LENGTH_SHORT).show(); // 토스트 메세지를 이용해 사용자에게 알림

    }
}