package qldt.dao;

import java.util.List;

import qldt.models.NhaCungCap;

public interface NhaCungCapDAO {
    NhaCungCap save(NhaCungCap nhaCungCap);
    NhaCungCap update(NhaCungCap nhaCungCap);
    boolean delete(String maNCC);
    NhaCungCap findById(String maNCC);
    List<NhaCungCap> findAll();
}