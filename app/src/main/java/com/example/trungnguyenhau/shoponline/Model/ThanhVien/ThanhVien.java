package com.example.trungnguyenhau.shoponline.Model.ThanhVien;

/**
 * Created by TRUNGNGUYENHAU on 4/22/2017.
 */

public class ThanhVien {
    String tenTV, tenDangNhap, matKhau, diaChi, ngaySinh, SDT
            , gioiTinh, cmnd;

    public ThanhVien() {
    }

    public ThanhVien(String tenTV, String tenDangNhap, String matKhau, String diaChi,
                     String ngaySinh, String SDT, String gioiTinh, String cmnd) {
        this.tenTV = tenTV;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.diaChi = diaChi;
        this.ngaySinh = ngaySinh;
        this.SDT = SDT;
        this.gioiTinh = gioiTinh;
        this.cmnd = cmnd;
    }

    public String getTenTV() {
        return tenTV;
    }

    public void setTenTV(String tenTV) {
        this.tenTV = tenTV;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }
}
