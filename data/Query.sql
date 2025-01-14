CREATE DATABASE IF NOT EXISTS QuanLyDienThoai;

USE QuanLyDienThoai;

CREATE TABLE NHACUNGCAP (
    MANCC VARCHAR(255) PRIMARY KEY,
    TENNHACC VARCHAR(255),
    DIACHI VARCHAR(255),
    SODIENTHOAI VARCHAR(15)
);

CREATE TABLE DIENTHOAI (
    MADT VARCHAR(255) PRIMARY KEY,
    TENDT VARCHAR(255),
    NAMSANXUAT VARCHAR(4),
    CAUHINH VARCHAR(255),
    MANCC VARCHAR(255),
    HINHANH VARCHAR(255),
    FOREIGN KEY (MANCC) REFERENCES NHACUNGCAP(MANCC)
);
