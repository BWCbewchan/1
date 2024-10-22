package qldt.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "nhacungcap")
public class NhaCungCap {

    @Id
    @Column(name = "mancc")
    @NotNull(message = "Mã nhà cung cấp phải không null")
    @NotEmpty(message = "Mã nhà cung cấp phải không rỗng")
    @Size(max = 255, message = "Mã nhà cung cấp tối đa 255 ký tự")
    private String maNCC;

    @Column(name = "tennhacc")
    @NotEmpty(message = "Tên nhà cung cấp phải không rỗng")
    @Size(max = 255, message = "Tên nhà cung cấp tối đa 255 ký tự")
    private String tenNCC;

    @Column(name = "diachi")
    @NotEmpty(message = "Địa chỉ phải không rỗng")
    @Size(max = 255, message = "Địa chỉ tối đa 255 ký tự")
    private String diaChi;

    @Column(name = "sodienthoai")
    @NotEmpty(message = "Số điện thoại phải không rỗng")
    @Size(max = 15, message = "Số điện thoại tối đa 15 ký tự")
    private String soDienThoai;

    @OneToMany(mappedBy = "nhaCungCap", cascade = CascadeType.ALL)
    private List<DienThoai> dienThoaiList; // List of associated DienThoai

    // Constructors, getters, and setters
    public NhaCungCap() {
    }

    public NhaCungCap(String maNCC, String tenNCC, String diaChi, String soDienThoai) {
        this.maNCC = maNCC;
        this.tenNCC = tenNCC;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
    }

    // Getters and setters
    public String getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    public String getTenNCC() {
        return tenNCC;
    }

    public void setTenNCC(String tenNCC) {
        this.tenNCC = tenNCC;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public List<DienThoai> getDienThoaiList() {
        return dienThoaiList;
    }

    public void setDienThoaiList(List<DienThoai> dienThoaiList) {
        this.dienThoaiList = dienThoaiList;
    }
}
