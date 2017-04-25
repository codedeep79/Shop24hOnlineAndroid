package com.example.trungnguyenhau.shoponline.Views.DangNhap_DangKy;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trungnguyenhau.shoponline.Model.ThanhVien.ThanhVien;
import com.example.trungnguyenhau.shoponline.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;


import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener ,GoogleApiClient.OnConnectionFailedListener{
    EditText edtTenDangNhap, edtMatKhauDangNhap;
    Button btnDangNhap;
    private SignInButton btnSignIn;
    TextView txtQuenMatKhau, txtDangKy;

    String tenDangNhap, matKhau;

    private static int RC_SIGN_IN = 0;
    private static String TAG = "LOGIN_ACTIVITY";
    private GoogleApiClient _googleApiClient;
    private FirebaseAuth _Auth;
    private FirebaseAuth.AuthStateListener _AuthListener;

    private LoginButton btnLoginFaceook;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
        addControls();
        addEvents();

    }


    private void addControls() {
        edtTenDangNhap = (EditText) findViewById(R.id.edittext_tendangnhap);
        edtMatKhauDangNhap = (EditText) findViewById(R.id.edittext_matkhaudangnhap);
        btnDangNhap = (Button) findViewById(R.id.button_dangnhap);
        btnSignIn = (SignInButton) findViewById(R.id.sign_in_button);
        txtQuenMatKhau = (TextView) findViewById(R.id.textview_quenmatkhau);
        txtDangKy = (TextView) findViewById(R.id.textview_dangky);

        // Login Facebook
        btnLoginFaceook = (LoginButton) findViewById(R.id.login_button);
        callbackManager = CallbackManager.Factory.create();
        loginWithFacebook();




        // Login Google Plus
        _Auth = FirebaseAuth.getInstance();
        //Trạng thái đăng nhập của google login
        _AuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null)
                {
                    Log.d("AUTH", "User logged in: " + user.getEmail());
                }
                else
                {
                    Log.d("AUTH", "User logged out");
                }
            }
        };

        // Yêu cầu lấy ra các thông tin để đăng nhập
       GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

       _googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

    }

    private void addEvents() {
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tenDangNhap = edtTenDangNhap.getText().toString();
                matKhau = edtMatKhauDangNhap.getText().toString();

                //  Toast.makeText(LoginActivity.this, tenDangNhap, Toast.LENGTH_SHORT).show();
                if (tenDangNhap.length() != 0 && matKhau.length() != 0)
                {
                    ThanhVien thanhVien = new ThanhVien();
                    thanhVien.setTenDangNhap(tenDangNhap);
                    thanhVien.setMatKhau(matKhau);
                    dangNhapThanhVienTask task = new dangNhapThanhVienTask();
                    task.execute(thanhVien);
                }
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        txtDangKy.setOnClickListener(this);
        txtQuenMatKhau.setOnClickListener(this);
    }


    private void loginWithFacebook(){
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(LoginActivity.this
                        , "Login success\n" + loginResult.getAccessToken()
                        , Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancel() {
                Toast.makeText(LoginActivity.this, "Login cancelled!", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(LoginActivity.this, "Login error: " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
   @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN)
        {
            // Lây kết quả trả về từ intent trước (intent đăng nhập google)
            GoogleSignInResult _googleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (_googleSignInResult.isSuccess())
            {
                GoogleSignInAccount _googleSignInAccount = _googleSignInResult.getSignInAccount();

                // Xác nhận google sau khi hoàn tất
                firebaseAuthWithGoogle(_googleSignInAccount);
            }
            else
            {
                Toast.makeText(LoginActivity.this, "Google login failed", Toast.LENGTH_LONG).show();
            }
        }


       callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount googleSignInAccount) {
        AuthCredential _auAuthCredential = GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(), null);
        _Auth.signInWithCredential(_auAuthCredential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        Log.d("AUTH", "Sign In With Credential: Completed, " + task.isSuccessful());

                    }
                });
    }

    ///////////////////////
    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(_googleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void signOut() {
        FirebaseAuth.getInstance().signOut();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (_AuthListener != null){
            _Auth.removeAuthStateListener(_AuthListener);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        _Auth.addAuthStateListener(_AuthListener);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId())
        {
            case R.id.textview_dangky:
                intent = new Intent(LoginActivity.this, DangKy.class);
                break;
            case R.id.textview_quenmatkhau:
                intent = new Intent(LoginActivity.this, QuenMatKhau.class);
                break;

        }
        startActivity(intent);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "Connection failed");
    }



    class dangNhapThanhVienTask extends AsyncTask<ThanhVien, Void, Boolean> {

        @Override
        protected Boolean doInBackground(ThanhVien... params) {
            URL url = null;
            try {
                url = new URL("http://192.168.0.101/ThuongMaiDienTu/ThuongMaiDienTu-24h.asmx/DangNhapThanhVien");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");

                ThanhVien thanhVien = params[0];

                // Khi đưa dữ liệu lên server, bắt buột phải mã hóa nó, đặc biệt là chuỗi. Kiểu int có thể không cần
                // mã hóa
                String thamSo = "email="+URLEncoder.encode(thanhVien.getTenDangNhap())+"&matKhau="
                        +URLEncoder.encode(thanhVien.getMatKhau())+"";

                OutputStream outputStream = httpURLConnection.getOutputStream();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
                outputStreamWriter.write(thamSo); // Dữ liệu truyền vào là thamSo ở trên

                outputStreamWriter.flush();
                outputStreamWriter.close();

                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                Document doc = documentBuilder.parse(httpURLConnection.getInputStream());

                NodeList nodeList = doc.getElementsByTagName("boolean");

                boolean kq = Boolean.parseBoolean(nodeList.item(0).getTextContent());
                return kq;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }

            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            if (aBoolean.booleanValue())
            {
                Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_LONG).show();

            }
            else
            {
                Toast.makeText(LoginActivity.this, "Đăng nhập thất bại, vui lòng thử lại", Toast.LENGTH_LONG).show();
            }
        }
    }
}
