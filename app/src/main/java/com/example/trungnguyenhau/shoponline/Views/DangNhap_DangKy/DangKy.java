package com.example.trungnguyenhau.shoponline.Views.DangNhap_DangKy;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.trungnguyenhau.shoponline.Model.ThanhVien.ThanhVien;
import com.example.trungnguyenhau.shoponline.R;
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
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class DangKy extends AppCompatActivity {
    private LinearLayout giao_dien_dang_ky;
    private Button btnDangKy;
    private EditText edtTenThanhVien, edtEmail, edtMatKhau, edtDiaChi, edtSoDienThoai, edtChungMinhThu;
    private Spinner spinnerNgaySinh, spinnerThangSinh, spinnerNamSinh, spinnerGioiTinh;

    private ArrayAdapter<String> adapterNgaySinh, adapterThangSinh, adapterNamSinh, adapterGioiTinh;
    private List<String> listNgaySinh, listThangSinh, listNamSinh, listGioiTinh;
    private String tenThanhVien, email, matKhau, diaChi, soDienThoai,
            chungMinhThu, ngaySinh, thangSinh, namSinh, gioiTinh;

    private WaveDrawable mWaveDangky;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        addControls();
        addEvents();
    }

    private void addEvents() {

        spinnerGioiTinh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                gioiTinh = spinnerGioiTinh.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerNgaySinh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ngaySinh = spinnerNgaySinh.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerThangSinh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                thangSinh = spinnerThangSinh.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        spinnerNamSinh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                namSinh = spinnerNamSinh.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tenThanhVien = edtTenThanhVien.getText().toString();
                email = edtEmail.getText().toString();
                matKhau = edtMatKhau.getText().toString();
                diaChi = edtDiaChi.getText().toString();
                soDienThoai = edtSoDienThoai.getText().toString();
                chungMinhThu = edtChungMinhThu.getText().toString();

                // Định dạng ngày tháng như dưới, nếu không server báo lỗi
                ngaySinh = ngaySinh + "/" + thangSinh + "/" + namSinh;
                if (tenThanhVien.length() != 0 && email.length() != 0 && matKhau.length() != 0
                        && diaChi.length() != 0 && chungMinhThu.length() != 0){

                    //Toast.makeText(DangKy.this, ngaySinh, Toast.LENGTH_LONG).show();
                    ThanhVien thanhVien = new ThanhVien();

                    thanhVien.setTenTV(tenThanhVien);
                    thanhVien.setTenDangNhap(email);
                    thanhVien.setMatKhau(matKhau);
                    thanhVien.setDiaChi(diaChi);
                    thanhVien.setCmnd(chungMinhThu);
                    thanhVien.setNgaySinh(ngaySinh);
                    thanhVien.setSDT(soDienThoai);
                    thanhVien.setGioiTinh(gioiTinh);

                    themThanhVienTask taskThemThanhVien = new themThanhVienTask();
                    taskThemThanhVien.execute(thanhVien);



                }
                else
                {
                    Toast.makeText(DangKy.this, "Vui lòng nhập lại", Toast.LENGTH_LONG).show();
                }




            }
        });
    }

    private void addControls() {
        giao_dien_dang_ky = (LinearLayout) findViewById(R.id.giao_dien_dang_ky);
        //mWaveDangky = new WaveDrawable(DangKy.this, R.drawable.ecommerce);
        //giao_dien_dang_ky.setBackground(mWaveDangky);
        //mWaveDangky.setIndeterminate(true);

        btnDangKy = (Button) findViewById(R.id.button_dangky);
        edtTenThanhVien = (EditText) findViewById(R.id.edittext_tenthanhvien);
        edtEmail = (EditText) findViewById(R.id.edittext_tenthanhvien);
        edtMatKhau = (EditText) findViewById(R.id.edittext_tenthanhvien);
        edtDiaChi = (EditText) findViewById(R.id.edittext_tenthanhvien);
        edtSoDienThoai = (EditText) findViewById(R.id.edittext_tenthanhvien);
        edtChungMinhThu = (EditText) findViewById(R.id.edittext_tenthanhvien);

        spinnerNgaySinh = (Spinner) findViewById(R.id.spinner_ngaysinh);
        spinnerThangSinh = (Spinner) findViewById(R.id.spinner_thangsinh);
        spinnerNamSinh = (Spinner) findViewById(R.id.spinner_namsinh);
        spinnerGioiTinh = (Spinner) findViewById(R.id.spinner_gioitinh);

        listNgaySinh = new ArrayList<>();
        listThangSinh = new ArrayList<>();
        listNamSinh = new ArrayList<>();
        listGioiTinh = new ArrayList<>();


        for(int _ngaySinh = 1; _ngaySinh <= 31; ++_ngaySinh)
        {
            listNgaySinh.add(_ngaySinh + "");
        }

        for(int _thangSinh = 1; _thangSinh <= 31; ++_thangSinh)
        {
            listThangSinh.add(_thangSinh + "");
        }

        for(int _namSinh = 1920; _namSinh <= 2017; ++_namSinh)
        {
            listNamSinh.add(_namSinh + "");
        }

        listGioiTinh.add("Nam");
        listGioiTinh.add("Nữ");
        listGioiTinh.add("Khác");
        adapterGioiTinh = new ArrayAdapter<String>(DangKy.this, android.R.layout.simple_spinner_item,listGioiTinh);
        adapterGioiTinh.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinnerGioiTinh.setAdapter(adapterGioiTinh);

        adapterNgaySinh = new ArrayAdapter<String>(DangKy.this, android.R.layout.simple_spinner_item ,listNgaySinh);
        adapterNgaySinh.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinnerNgaySinh.setAdapter(adapterNgaySinh);

        adapterThangSinh = new ArrayAdapter<String>(DangKy.this, android.R.layout.simple_spinner_item,listThangSinh);
        adapterThangSinh.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinnerThangSinh.setAdapter(adapterThangSinh);

        adapterNamSinh = new ArrayAdapter<String>(DangKy.this, android.R.layout.simple_spinner_item,listNamSinh);
        adapterNamSinh.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinnerNamSinh.setAdapter(adapterNamSinh);

    }

    class themThanhVienTask extends AsyncTask<ThanhVien, Void, Boolean>{

        @Override
        protected Boolean doInBackground(ThanhVien... params) {
            URL url = null;
            try {
                url = new URL("http://192.168.0.101/ThuongMaiDienTu/ThuongMaiDienTu-24h.asmx/DangKyThanhVien");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");

                ThanhVien thanhVien = params[0];

                // Khi đưa dữ liệu lên server, bắt buột phải mã hóa nó, đặc biệt là chuỗi. Kiểu int có thể không cần
                // mã hóa
                 String thamSo = "tenTV="+ URLEncoder.encode(thanhVien.getTenTV())+"&tenDangNhap="+URLEncoder.encode(thanhVien.getTenDangNhap())
                        +"&matKhau="+URLEncoder.encode(thanhVien.getMatKhau())+"&diaChi="+URLEncoder.encode(thanhVien.getDiaChi())+"&ngaySinh="
                        +URLEncoder.encode(thanhVien.getNgaySinh())+"&SDT="+URLEncoder.encode(thanhVien.getSDT())+"&gioiTinh="+URLEncoder.encode(thanhVien.getGioiTinh())
                        +"&cmnd="+URLEncoder.encode(thanhVien.getCmnd())+"";

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
                Toast.makeText(DangKy.this, "Đăng ký thành công", Toast.LENGTH_LONG).show();
                finish();
            }
            else
            {
                Toast.makeText(DangKy.this, "Đăng ký thất bại, vui lòng thử lại", Toast.LENGTH_LONG).show();
            }
        }
    }
}
