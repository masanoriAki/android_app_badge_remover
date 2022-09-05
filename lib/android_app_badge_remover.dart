import 'dart:async';
import 'package:flutter/services.dart';

class AndroidAppBadgeRemover {
  static const MethodChannel _channel =
      const MethodChannel('android_app_badge_remover');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }
}
