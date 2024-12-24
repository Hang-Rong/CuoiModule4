package com.nhadat.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Transaction {
    @Id
    @NotBlank(message = "Mã giao dịch là bắt buộc")
    @Pattern(regexp = "MGD-\\d{4}", message = "Mã giao dịch phải có định dạng MGD-XXXX")
    private String maGiaoDich;

    @ManyToOne
    @JoinColumn(name = "khach_hang_id")
    @NotNull(message = "Tên khách hàng là bắt buộc")
    private Customer customer;

    @NotBlank(message = "Loại dịch vụ là bắt buộc")
    private String loaiDichVu;

    @NotNull(message = "Ngày giao dịch là bắt buộc")
    private LocalDate ngayGiaoDich;

    @Min(value = 500000, message = "Đơn giá phải lớn hơn 500.000 VND")
    private double donGia;

    @Min(value = 20, message = "Diện tích phải lớn hơn 20 m²")
    private double dienTich;

}
