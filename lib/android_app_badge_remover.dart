import 'dart:async';
import 'package:flutter/services.dart';

class AndroidAppBadgeRemover {
  static const MethodChannel _channel =
      const MethodChannel('android_app_badge_remover');

  static Future<void> appBadgeRemover({bool debug = false}) async {
    final bool _result = await _channel.invokeMethod('appBadgeRemover');
    if (_result && debug) print("appBadgeRemover----------success");
  }
}
