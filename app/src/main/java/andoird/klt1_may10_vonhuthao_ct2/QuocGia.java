package andoird.klt1_may10_vonhuthao_ct2;

public class QuocGia {
    public int getHinh() {
        return hinh;
    }

    public void setHinh(int hinh) {
        this.hinh = hinh;
    }

    int hinh;
    String ten;
    String dienTich;
    long  danSo;
    String thuDo;
    String chauLuc;
    public QuocGia(){

    }
    public QuocGia(int hinh,String ten, String dienTich, long danSo, String thuDo, String chauLuc){
        this.hinh = hinh;
        this.ten = ten;
        this.dienTich = dienTich;
        this.danSo = danSo;
        this.thuDo = thuDo;
        this.chauLuc = chauLuc;
    }
    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDienTich() {
        return dienTich;
    }

    public void setDienTich(String dienTich) {
        this.dienTich = dienTich;
    }

    public long getDanSo() {
        return danSo;
    }

    public void setDanSo(long danSo) {
        this.danSo = danSo;
    }

    public String getThuDo() {
        return thuDo;
    }

    public void setThuDo(String thuDo) {
        this.thuDo = thuDo;
    }

    public String getChauLuc() {
        return chauLuc;
    }

    public void setChauLuc(String chauLuc) {
        this.chauLuc = chauLuc;
    }

}
