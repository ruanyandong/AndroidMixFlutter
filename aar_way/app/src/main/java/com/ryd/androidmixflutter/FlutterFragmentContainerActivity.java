package com.ryd.androidmixflutter;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.ryd.androidmixflutter.aar.R;

import io.flutter.embedding.android.FlutterFragment;
import io.flutter.embedding.android.RenderMode;
import io.flutter.embedding.android.TransparencyMode;


public class FlutterFragmentContainerActivity extends AppCompatActivity {

    // Define a tag String to represent the FlutterFragment within this
    // Activity's FragmentManager. This value can be whatever you'd like.
    private static final String TAG_FLUTTER_FRAGMENT = "flutter_fragment";

    // Declare a local variable to reference the FlutterFragment so that you
    // can forward calls to it later.
    private FlutterFragment flutterFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flutter_fragment_container);

        // Get a reference to the Activity's FragmentManager to add a new
        // FlutterFragment, or find an existing one.
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Attempt to find an existing FlutterFragment,
        // in case this is not the first time that onCreate() was run.
        flutterFragment = (FlutterFragment) fragmentManager
                .findFragmentByTag(TAG_FLUTTER_FRAGMENT);

        // Create and attach a FlutterFragment if one does not exist.
        if (flutterFragment == null) {
            //代码会以 main() 为 Dart 入口函数， / 为初始路由，并使用新的 FlutterEngine
            // flutterFragment = FlutterFragment.createDefault();

            flutterFragment = FlutterFragment
                    // 使用缓存预热引擎
                    .withCachedEngine("my_engine_id")
                    // 使用新引擎
                    // .withNewEngine()

                    // 初始化路由，但是如果使用预热引擎，则此设置无效
                    //.initialRoute("myInitialRoute/")

                    // 指定 Flutter 运行的入口,但是如果使用预热引擎，则此设置无效
                    // .dartEntrypoint("mySpecialEntrypoint")

                    //控制 FlutterFragment 的渲染模式
                    .renderMode(RenderMode.texture)
                    //展示透明的 FlutterFragment
                    .transparencyMode(TransparencyMode.transparent)
                    //FlutterFragment 自身包含一种特性，可以用于决定 FlutterFragment 是否应该控制宿主 Activity，或者只影响自身行为。要预防 FlutterFragment 将其 Activity 暴露给 Flutter 插件，以免 Flutter 控制 Activity 的系统 UI，可以使用 FlutterFragment 的 Builder 中的 shouldAttachEngineToActivity() 方法
                    // 传递 false 给 Builder 的 shouldAttachEngineToActivity() 方法，可防止 Flutter 与所属的 Activity 交互。默认值为 true，此时允许 Flutter 和 Flutter 插件与 Activity 交互。
                    // 一些插件可能期望或确实需要一个 Activity 的引用。所以在禁用 Activity 的访问权限之前，请确保没有插件需要。
                    .shouldAttachEngineToActivity(false)
                    .build();

            fragmentManager
                    .beginTransaction()
                    .add(
                            R.id.flutter_fragment_container,
                            flutterFragment,
                            TAG_FLUTTER_FRAGMENT
                    )
                    .commit();
        }

    }

    @Override
    public void onPostResume() {
        super.onPostResume();
        flutterFragment.onPostResume();
    }

    @Override
    protected void onNewIntent(@NonNull Intent intent) {
        super.onNewIntent(intent);
        flutterFragment.onNewIntent(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        flutterFragment.onBackPressed();
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            @NonNull String[] permissions,
            @NonNull int[] grantResults
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        flutterFragment.onRequestPermissionsResult(
                requestCode,
                permissions,
                grantResults
        );
    }

    @Override
    public void onUserLeaveHint() {
        super.onUserLeaveHint();
        flutterFragment.onUserLeaveHint();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        flutterFragment.onTrimMemory(level);
    }
}