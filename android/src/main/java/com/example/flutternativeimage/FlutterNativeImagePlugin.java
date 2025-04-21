package com.example.flutternativeimage;

import android.content.Context;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodChannel;

public class FlutterNativeImagePlugin implements FlutterPlugin {
  private static final String CHANNEL_NAME = "flutter_native_image";
  private MethodChannel channel;
  private MethodCallHandlerImpl methodCallHandler;

  @Override
  public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding binding) {
    setupChannel(binding.getBinaryMessenger(), binding.getApplicationContext());
  }

  @Override
  public void onDetachedFromEngine(FlutterPlugin.FlutterPluginBinding binding) {
    teardownChannel();
  }

  private void setupChannel(BinaryMessenger messenger, Context context) {
    channel = new MethodChannel(messenger, CHANNEL_NAME);
    methodCallHandler = new MethodCallHandlerImpl(context);
    channel.setMethodCallHandler(methodCallHandler);
  }

  private void teardownChannel() {
    if (methodCallHandler != null) {
      //methodCallHandler.cleanup();
      methodCallHandler = null;
    }
    if (channel != null) {
      channel.setMethodCallHandler(null);
      channel = null;
    }
  }
}