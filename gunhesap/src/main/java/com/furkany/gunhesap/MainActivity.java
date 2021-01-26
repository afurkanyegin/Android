package com.furkany.gunhesap;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    EditText etgun,etay,etyil;
    TextView tvsonuc;
    int gun,ay,yil;

    Button btnhesapla;
    int[] aylar={31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    int sonucgun,sonucay,sonucyil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etgun=findViewById(R.id.et_gun);
        etay=findViewById(R.id.et_ay);
        etyil=findViewById(R.id.et_yil);
        tvsonuc=findViewById(R.id.tv_sonuc);
        btnhesapla=findViewById(R.id.btn_hesapla);

        btnhesapla.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                gun = Integer.parseInt(etgun.getText().toString());
                ay = Integer.parseInt(etay.getText().toString());
                yil = Integer.parseInt(etyil.getText().toString());

                int bugun= Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
                int buay = Calendar.getInstance().get(Calendar.MONTH);
                int buyil= Calendar.getInstance().get(Calendar.YEAR);
                buay++;

                if(yil<=buyil&&ay<=12&&(((ay==1||ay==3||ay==5||ay==7||ay==8||ay==10||ay==12)&&gun<=31)||((ay==4||ay==6||ay==9||ay==11)&&gun<=30)||(ay==2&&(yil%4==0)&&gun<=29)||(ay==2&&(yil%4!=0)&&gun<=28))) {


                    int toplam = 0;
                    int sayacyil = yil;
                    int sayacay = ay;
                    int aykontrol = 12;
                    boolean buyilmi = true;


                    while (sayacyil <= buyil) {
                        if (sayacyil == buyil) {
                            buyilmi = true;
                            aykontrol = buay - 1;
                        }
                        if (buyilmi == true) {
                            while (sayacay <= aykontrol) {
                                if (sayacay == 1 || sayacay == 3 || sayacay == 5 || sayacay == 7 || sayacay == 8 || sayacay == 10 || sayacay == 12)
                                    toplam += 31;
                                else if (sayacay == 4 || sayacay == 6 || sayacay == 9 || sayacay == 11)
                                    toplam += 30;
                                else {
                                    if (sayacyil % 4 == 0) toplam += 29;
                                    else toplam += 28;
                                }
                                sayacay++;
                            }
                            sayacay = 1;
                            buyilmi = false;
                        } else {
                            if (sayacyil % 4 == 0) toplam += 366;
                            else toplam += 365;
                        }
                        sayacyil++;
                    }
                    toplam += bugun - gun;
                    tvsonuc.setText(toplam + "gün " + bugun + buay + buyil);
                }
                else{
                    tvsonuc.setText("Hatalı Giriş !");
                }
            }
        });



    }
}
