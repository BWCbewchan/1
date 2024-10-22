package qldt.dao;

import java.util.List;

import qldt.models.DienThoai;

public interface DienThoaiDAO {
    DienThoai save(DienThoai dienThoai);
    DienThoai update(DienThoai dienThoai);
    boolean delete(String maDT);
    DienThoai findById(String maDT);
    List<DienThoai> findAll();
	List<DienThoai> searchByKeyword(String keyword);
}
