package com.cnsunrun.alipayutils.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cnsunrun.alipaylibrary.alipay.AliPayUtils;
import com.cnsunrun.alipayutils.utils.ConstantValue;
import com.cnsunrun.alipayutils.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

    }

    private void initViews() {
        TextView tv_to_pay = (TextView) findViewById(R.id.tv_to_pay);
        tv_to_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toPayFromAliPay();
            }
        });

    }

    private void toPayFromAliPay() {
        String orderTitle = "测试支付宝";
        String orderNumber = "2017080410248504";
        String totalPrice = "0.01";
        String urlCallback = ConstantValue.ALIPAY_URL_CALLBACK;
        String APP_ID = ConstantValue.ALI_APPID;
        String SELLER_ID = ConstantValue.SELLER_ID;
        String RSA_PRIVATE2 = ConstantValue.RSA_PRIVATE2;
        AliPayUtils alipay = new AliPayUtils(APP_ID, SELLER_ID, RSA_PRIVATE2, this);
        alipay.requestPay(orderTitle, orderNumber, totalPrice, urlCallback);
        alipay.setPayListener(new AliPayUtils.OnAlipayListener() {
            @Override
            public void onSuccess() {
                super.onSuccess();
                Toast.makeText(MainActivity.this, "支付成功", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancel() {
                super.onCancel();
                Toast.makeText(MainActivity.this, "取消支付", Toast.LENGTH_LONG).show();
            }
        });

    }
}
