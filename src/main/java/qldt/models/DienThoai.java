package qldt.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "dienthoai")
public class DienThoai {

    @Id
    @Column(name = "madt")
    @NotNull(message = "Mã điện thoại phải không null")
    @NotEmpty(message = "Mã điện thoại phải không rỗng")
    @Size(max = 255, message = "Mã điện thoại tối đa 255 ký tự")
    private String maDT;

    @Column(name = "tendt")
    @NotEmpty(message = "Tên điện thoại phải không rỗng")
    @Size(max = 255, message = "Tên điện thoại tối đa 255 ký tự")
    private String tenDT;

    @Column(name = "namsanxuat")
    @NotNull(message = "Năm sản xuất không được để trống.")
    @Min(value = 1900, message = "Năm sản xuất phải lớn hơn hoặc bằng 1900.")
    @Max(value = 2100, message = "Năm sản xuất phải nhỏ hơn hoặc bằng 2100.")
    private int namSanXuat;

    @Column(name = "cauhinh")
    @NotEmpty(message = "Cấu hình phải không rỗng")
    @Size(max = 255, message = "Cấu hình tối đa 255 ký tự")
    private String cauHinh;

    @ManyToOne
    @JoinColumn(name = "mancc", referencedColumnName = "mancc", nullable = false)
    @NotNull(message = "Mã nhà cung cấp phải không null")
    private NhaCungCap nhaCungCap; // Relationship field

    @Column(name = "hinhanh")
    @NotEmpty(message = "Hình ảnh phải không rỗng")
    @Size(max = 255, message = "Hình ảnh tối đa 255 ký tự")
    private String hinhAnh;

    // Constructors, getters, and setters
    public DienThoai() {
    }

    public DienThoai(String maDT, String tenDT, int namSanXuat, String cauHinh, NhaCungCap nhaCungCap,
                     String hinhAnh) {
        this.maDT = maDT;
        this.tenDT = tenDT;
        this.namSanXuat = namSanXuat;
        this.cauHinh = cauHinh;
        this.nhaCungCap = nhaCungCap;
        this.hinhAnh = hinhAnh;
    }

    public DienThoai(String maDT2, String tenDT2, String namSanXuatStr, String thongTinCauHinh, String fileName) {
		// TODO Auto-generated constructor stub
	}

	// Getters and setters
    public String getMaDT() {
        return maDT;
    }

    public void setMaDT(String maDT) {
        this.maDT = maDT;
    }

    public String getTenDT() {
        return tenDT;
    }

    public void setTenDT(String tenDT) {
        this.tenDT = tenDT;
    }

    public int getNamSanXuat() {
        return namSanXuat;
    }

    public void setNamSanXuat(int namSanXuat) {
        this.namSanXuat = namSanXuat;
    }

    public String getCauHinh() {
        return cauHinh;
    }

    public void setCauHinh(String cauHinh) {
        this.cauHinh = cauHinh;
    }

    public NhaCungCap getNhaCungCap() {
        return nhaCungCap;
    }

    public void setNhaCungCap(NhaCungCap nhaCungCap) {
        this.nhaCungCap = nhaCungCap;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }
}
