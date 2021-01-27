package com.ryd.androidmixflutter;

import android.app.Application;

import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterEngineCache;
import io.flutter.embedding.engine.dart.DartExecutor;

/**
 * @author -> Wings
 * @date -> 2021/1/27
 * @email -> ruanyandongai@gmail.com 729368173@qq.com
 * @phone -> 18983790146
 * @blog -> https://ruanyandong.github.io https://blog.csdn.net/qq_34681580
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Instantiate a FlutterEngine.
        FlutterEngine flutterEngine = new FlutterEngine(this);
        // Configure an initial route.
        flutterEngine.getNavigationChannel().setInitialRoute("/my_route");
        // Start executing Dart code to pre-warm the FlutterEngine.
        flutterEngine.getDartExecutor().executeDartEntrypoint(DartExecutor.DartEntrypoint.createDefault());

        // Cache the FlutterEngine to be used by FlutterActivity.
        FlutterEngineCache.getInstance().put("my_engine_id", flutterEngine);
    }
}
