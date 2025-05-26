
import entity.DichVu;

public class test {
    public static void main(String[] args) {
        // Tạo đối tượng DichVu
        DichVu dichVu1 = new DichVu("DV00", "Giặt ủi", 50000, "Giặt và ủi quần áo trong ngày, nhận tại phòng.");

        // In thông tin dịch vụ
        System.out.println(dichVu1.toString());
    }
}
