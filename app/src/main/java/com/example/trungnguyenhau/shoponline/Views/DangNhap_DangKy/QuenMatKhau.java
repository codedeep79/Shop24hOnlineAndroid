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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.trungnguyenhau.shoponline.Model.ThanhVien.ThanhVien;
import com.example.trungnguyenhau.shoponline.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.race604.drawable.wave.WaveDrawable;

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

public class QuenMatKhau extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout giao_dien_nhap_email, giao_dien_nhap_mat_khau, giao_dien_quen_mat_khau;
    private EditText edtTenDangNhap, edtMatKhau, edtNhapLaiMatKhau;
    private Button btnXacNhanEmail, btnDoiMatKhau, btnSignIn;
    private ImageView imgbgGiaoDienQuenMatKhau;

    private WaveDrawable mWaveGiaoDienQuenMatKhau;

    String tenDangNhap, matKhau, nhapLaiMatKhau;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quen_mat_khau);
        addControls();
        addEvents();

    }

    private void addEvents() {
        btnDoiMatKhau.setOnClickListener(this);
        btnXacNhanEmail.setOnClickListener(this);
    }

    private void addControls() {
        giao_dien_quen_mat_khau = (LinearLayout) findViewById(R.id.giao_dien_quen_mat_khau);
        giao_dien_nhap_email = (LinearLayout) findViewById(R.id.giao_dien_nhap_email);
        giao_dien_nhap_mat_khau = (LinearLayout) findViewById(R.id.giao_dien_nhap_mat_khau);
        edtMatKhau = (EditText) findViewById(R.id.edittext_matkhau);
        edtTenDangNhap = (EditText) findViewById(R.id.edittext_tendangnhap);
        edtNhapLaiMatKhau = (EditText) findViewById(R.id.edittext_nhaplaimatkhau);
        btnXacNhanEmail = (Button) findViewById(R.id.button_xac_nhan_email);
        btnDoiMatKhau = (Button) findViewById(R.id.button_doi_mat_khau);

        imgbgGiaoDienQuenMatKhau = (ImageView) findViewById(R.id.imageview_bg_quenmatkhau);



        mWaveGiaoDienQuenMatKhau = new WaveDrawable(QuenMatKhau.this, R.drawable.ecommerce);
        imgbgGiaoDienQuenMatKhau.setImageDrawable(mWaveGiaoDienQuenMatKhau);
        mWaveGiaoDienQuenMatKhau.setIndeterminate(true);

    }





    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.button_doi_mat_khau:
                matKhau = edtMatKhau.getText().toString().trim();
                nhapLaiMatKhau = edtNhapLaiMatKhau.getText().toString().trim();

                if (matKhau.length() > 0 && nhapLaiMatKhau.length() > 0)
                {
                    if (matKhau.equals(nhapLaiMatKhau))
                    {
                        ThanhVien thanhVien = new ThanhVien();
                        thanhVien.setTenDangNhap(tenDangNhap);
                        thanhVien.setMatKhau(matKhau);
                        LayLaiMatKhauTask task = new LayLaiMatKhauTask();
                        task.execute(thanhVien);
                    }
                    else
                    {
                        Toast.makeText(QuenMatKhau.this, "Vui lòng nhập đúng mật khẩu tại hai ô mật khẩu", Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(QuenMatKhau.this, "Vui lòng nhập lại mật khẩu", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.button_xac_nhan_email:
                tenDangNhap = edtTenDangNhap.getText().toString().trim();

                if (tenDangNhap.length() != 0)
                {
                    ThanhVien thanhVien = new ThanhVien();
                    thanhVien.setTenDangNhap(tenDangNhap);
                    XacNhanEmailTask task = new XacNhanEmailTask();
                    task.execute(thanhVien);
                }
                break;
        }
    }



    class XacNhanEmailTask extends AsyncTask<ThanhVien, Void, Boolean>
    {

        @Override
        protected Boolean doInBackground(ThanhVien... params) {
            URL url = null;
            try {
                url = new URL("http://192.168.0.101/ThuongMaiDienTu/ThuongMaiDienTu-24h.asmx/trungTenDangNhap");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");

                ThanhVien thanhVien = params[0];

                // Khi đưa dữ liệu lên server, bắt buột phải mã hóa nó, đặc biệt là chuỗi. Kiểu int có thể không cần
                // mã hóa
                String thamSo = "email="+ URLEncoder.encode(thanhVien.getTenDangNhap())+"";

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
                giao_dien_nhap_mat_khau.setVisibility(View.VISIBLE);
                giao_dien_nhap_email.setVisibility(View.INVISIBLE);

                Toast.makeText(QuenMatKhau.this,
                        "Vui lòng nhập vào mật khẩu mới", Toast.LENGTH_LONG).show();

            }
            else
            {
                Toast.makeText(QuenMatKhau.this,
                        "Tên đăng nhập không tồn tại, vui lòng thử lại.", Toast.LENGTH_LONG).show();
            }
        }
    }

    class LayLaiMatKhauTask extends AsyncTask<ThanhVien, Void, Boolean>
    {

        @Override
        protected Boolean doInBackground(ThanhVien... params) {
            URL url = null;
            try {
                url = new URL("http://192.168.0.101/ThuongMaiDienTu/ThuongMaiDienTu-24h.asmx/SuaMatKhauThanhVien");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");

                ThanhVien thanhVien = params[0];

                // Khi đưa dữ liệu lên server, bắt buột phải mã hóa nó, đặc biệt là chuỗi. Kiểu int có thể không cần
                // mã hóa
                String thamSo = "email="+URLEncoder.encode(thanhVien.getTenDangNhap())+"&matKhau="+URLEncoder.encode(thanhVien.getMatKhau())+"";

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
                giao_dien_nhap_mat_khau.setVisibility(View.VISIBLE);
                giao_dien_nhap_email.setVisibility(View.INVISIBLE);

                Toast.makeText(QuenMatKhau.this,
                        "Thay đổi mật khẩu thành công. Vui lòng đăng nhập lại", Toast.LENGTH_LONG).show();

            }
            else
            {
                Toast.makeText(QuenMatKhau.this,
                        "Lỗi server", Toast.LENGTH_LONG).show();
            }
        }
    }
}
