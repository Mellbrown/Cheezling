package cf.g2utools.cheezling.activity.MainPages;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

import cf.g2utools.cheezling.Base;
import cf.g2utools.cheezling.R;
import cf.g2utools.cheezling.custom_view.BottomMenu;
import cf.g2utools.cheezling.custom_view.SmallTalkView;
import cf.g2utools.cheezling.data.AccountData;
import cf.g2utools.cheezling.data.RemoteData;
import cf.g2utools.cheezling.util.MyAccount;

public class NewSpeed extends AppCompatActivity implements SensorEventListener {

    BottomMenu bottomMenu;
    SmallTalkView smallTalkView;

    //Using the Accelometer & Gyroscoper
    private SensorManager mSensorManager = null;

    //Using the Gyroscope
    private Sensor mGgyroSensor = null;

    //Roll and Pitch
    private double pitch;
    private double roll;
    private double yaw;

    //timestamp and dt
    private double timestamp;
    private double dt;

    // for radian -> dgree
    private double RAD2DGR = 180 / Math.PI;
    private static final float NS2S = 1.0f/1000000000.0f;

    double mPit;
    double mRoll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_new_speed_page);

        bottomMenu = findViewById(R.id.bot);
        bottomMenu.Init(this, BottomMenu.Here.newSpeed);
        smallTalkView = findViewById(R.id.smallTalkView);
        smallTalkView.init(1000,75);
        smallTalkView.data.addAll(RemoteData.rootData.getThreadesData().toHtmles());
        Collections.reverse(smallTalkView.data);
        smallTalkView.notifyDatasetChanged();
        smallTalkView.reDrawing();

        //Using the Gyroscope & Accelometer
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        //Using the Accelometer
        mGgyroSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        mSensorManager.registerListener(this,mGgyroSensor,SensorManager.SENSOR_DELAY_UI);


    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
            /* 각 축의 각속도 성분을 받는다. */
        double gyroX = sensorEvent.values[0];
        double gyroY = sensorEvent.values[1];
        double gyroZ = sensorEvent.values[2];

            /* 각속도를 적분하여 회전각을 추출하기 위해 적분 간격(dt)을 구한다.
             * dt : 센서가 현재 상태를 감지하는 시간 간격
             * NS2S : nano second -> second */
        dt = (sensorEvent.timestamp - timestamp) * NS2S;
        timestamp = sensorEvent.timestamp;

            /* 맨 센서 인식을 활성화 하여 처음 timestamp가 0일때는 dt값이 올바르지 않으므로 넘어간다. */
        if (dt - timestamp*NS2S != 0) {

                /* 각속도 성분을 적분 -> 회전각(pitch, roll)으로 변환.
                 * 여기까지의 pitch, roll의 단위는 '라디안'이다.
                 * SO 아래 로그 출력부분에서 멤버변수 'RAD2DGR'를 곱해주어 degree로 변환해줌.  */
            pitch = pitch + gyroY*dt;
            roll = roll + gyroX*dt;
            yaw = yaw + gyroZ*dt;

            mPit = pitch*RAD2DGR;
            mRoll = roll*RAD2DGR;

            smallTalkView.setSlopeRatio(90.0+yaw*RAD2DGR);
            smallTalkView.reDrawing();
            Log.e("LOG", "GYROSCOPE           [X]:" + String.format("%.4f", sensorEvent.values[0])
                    + "           [Y]:" + String.format("%.4f", sensorEvent.values[1])
                    + "           [Z]:" + String.format("%.4f", sensorEvent.values[2])
                    + "           [Pitch]: " + String.format("%.1f", pitch*RAD2DGR)
                    + "           [Roll]: " + String.format("%.1f", roll*RAD2DGR)
                    + "           [Yaw]: " + String.format("%.1f", yaw*RAD2DGR)
                    + "           [dt]: " + String.format("%.4f", dt));

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
