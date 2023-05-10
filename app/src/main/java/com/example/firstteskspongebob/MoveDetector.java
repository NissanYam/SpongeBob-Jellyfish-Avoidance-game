package com.example.firstteskspongebob;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.Toast;

import com.example.firstteskspongebob.MySignal;
import com.example.firstteskspongebob.activities.GameActivity;
import com.example.firstteskspongebob.interfaces.CallBack_movement;

import java.text.DecimalFormat;

public class MoveDetector {
    private CallBack_movement callBack_movement;
    private SensorManager mySensorManager;
    private Sensor sensor;
    private int sensorType = Sensor.TYPE_ACCELEROMETER;

    public MoveDetector(Context context, CallBack_movement callBack_movement) {
        this.callBack_movement = callBack_movement;
        mySensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor = mySensorManager.getDefaultSensor(sensorType);
    }
    public void start() {
        mySensorManager.registerListener(sensorListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void stop() {
        mySensorManager.unregisterListener(sensorListener);
    }

    SensorEventListener sensorListener = new SensorEventListener() {
        long last = 0;
        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == sensorType) {
                if (System.currentTimeMillis() < last + 500) {
                    return;
                }
                float x = event.values[0];
                float y = event.values[1];
                callBack_movement.movePlayer(x);
            }
        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

    };

}
