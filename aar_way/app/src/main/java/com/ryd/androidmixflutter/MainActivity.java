package com.ryd.androidmixflutter;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.ryd.androidmixflutter.aar.R;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.android.FlutterActivityLaunchConfigs;

//https://flutter.cn/docs/development/add-to-app/android/project-setup
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button _btn_jump_to_flutter_activity_ = findViewById(R.id.btn);
        _btn_jump_to_flutter_activity_.setOnClickListener(v -> {
            startActivity(
                    //Dart 代码入口是调用 main()，并且你的 Flutter 初始路由是 ‘/’
                    //FlutterActivity.createDefaultIntent(MainActivity.this);

                    // 启动页面时会有延迟
                    //FlutterActivity.withNewEngine().initialRoute("/my_route").build(MainActivity.this)

                    // 使用缓存的flutterEngine
                    FlutterActivity
                            .withCachedEngine("my_engine_id")
                            .backgroundMode(FlutterActivityLaunchConfigs.BackgroundMode.transparent)
                            .build(MainActivity.this)
            );
        });

        Button _btn_jump_to_flutter_fragment_ = findViewById(R.id.btn1);
        _btn_jump_to_flutter_fragment_.setOnClickListener(v->{
            startActivity(new Intent(MainActivity.this, FlutterFragmentContainerActivity.class));
        });


    }
}