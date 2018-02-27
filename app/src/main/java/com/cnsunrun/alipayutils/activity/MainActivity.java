package com.cnsunrun.alipayutils.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cnsunrun.alipaylibrary.alipay.AliPayUtils;
import com.cnsunrun.alipayutils.R;
import com.cnsunrun.alipayutils.utils.ConstantValue;


/**
 * compile 'com.github.lucky-you:AliPayUtils:v1.0.7'
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

    }

    private void initViews() {
        TextView tv_to_pay_one = (TextView) findViewById(R.id.tv_to_pay_one);
        TextView tv_to_pay_two = (TextView) findViewById(R.id.tv_to_pay_two);
        tv_to_pay_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toPayFromAliPay();
            }
        });
        tv_to_pay_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toPayFromAliPayFromService();
            }
        });

    }

    private void toPayFromAliPayFromService() {

        String orderInfo = "alipay_sdk=alipay-sdk-php-20161101&app_id=2017121800946973&biz_content=%7B%22body%22%3A%22%5Cu5145%5Cu503c%22%2C%22subject%22%3A%22%5Cu5145%5Cu503c%22%2C%22timeout_express%22%3A%2230m%22%2C%22out_trade_no%22%3A%222018022710152985%22%2C%22total_amount%22%3A%2210%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fwww.gzwwzww.com%2FOpen%2FPay%2FAppAli%2Fnotify.html&sign_type=RSA2&timestamp=2018-02-27+14%3A47%3A58&version=1.0&sign=PdF0L6l4hHqghHCBs8nv%2BIADGJGOP%2FpGCHTbRwPq7QTF6MPDfkEj7WilnpzSpHTQvuHsLV4%2BkoX5p1GCzD853i8EIYDxfB19AAfNJ9N%2FkpPQqxSBE4mwTp9Zx3Z4FXNi5RtQylz%2FvVmywbi49G65pNgPnPEsnV8HC4lfPpUkXXtwxn%2BtrrQMYxku%2BZ2mTsCYFRZQrcXLC8f3Ky0UROIczmYBI%2FIHXezd2nGHCVzIfYEqZLWfDUodr7v7V238Fpk16mzcewV9oXuoOko%2Bteit2XEEPLLpE3edcpZTv5%2F5Ww7v%2FhNyDDh%2F2r4GHf31ZqiCKZ3OFQPLrJeIwNmI%2Bw%2FQdw%3D%3D\n";
        AliPayUtils aliPayUtils = new AliPayUtils(this);
        aliPayUtils.requestPayFromServiceSide(orderInfo);
        aliPayUtils.setPayListener(new AliPayUtils.OnAlipayListener() {
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
