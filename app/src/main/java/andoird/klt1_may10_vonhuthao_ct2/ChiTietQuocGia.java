package andoird.klt1_may10_vonhuthao_ct2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ChiTietQuocGia extends AppCompatActivity {
    ImageView img;
    TextView txt_tenQuocGia, txt_DienTich, txt_DanSo, txt_ThuDo, txt_ChauLuc;
    Button btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_quoc_gia);
        addControls();
        addEvents();
    }
    void addControls(){
        img = findViewById(R.id.img_ChiTietQuocgia);
        txt_tenQuocGia = findViewById(R.id.txt_tenChiTietQuocgia);
        txt_DienTich = findViewById(R.id.txt_DienTich);
        txt_DanSo = findViewById(R.id.txt_DanSo);
        txt_ThuDo = findViewById(R.id.txt_ThuDo);
        txt_ChauLuc = findViewById(R.id.txt_ChauLuc);
        btn_back = findViewById(R.id.btn_back);
    }
    void addEvents(){
        Intent intent = getIntent();
        int drawableId = intent.getIntExtra("hinh", R.drawable.hao2);
        img.setImageResource(drawableId);
        txt_tenQuocGia.setText("Tên: " + intent.getStringExtra("ten"));
        txt_DienTich.setText("Diện tích: " + intent.getStringExtra("dientich"));
        long danSo = getIntent().getLongExtra("danso", 0);
        String danSoText = String.valueOf(danSo);
        txt_DanSo.setText("Dân số: " + danSoText + " triệu");
        txt_ThuDo.setText("Thử đô: " +intent.getStringExtra("thudo"));
        txt_ChauLuc.setText("Châu lục: " + intent.getStringExtra("chauluc"));

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}