package web.android_app_badge_remover

// プラグインからメイン関数のActivityとcontextを使うにはこれらが必要
import android.app.Activity
import android.content.Context
import android.util.Log
// アプリバッジを削除する為に、以下を使用
import androidx.core.app.NotificationManagerCompat

import androidx.annotation.NonNull
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar

// contextを使う為の記述
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding

class AndroidAppBadgeRemoverPlugin: FlutterPlugin, MethodCallHandler, ActivityAware {
  private lateinit var channel : MethodChannel
  // 以下を追加する事で、MainActivityに紐づいたcontextを使えるっぽい？
  private lateinit var activity : Activity
  private lateinit var context : Context

  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, "android_app_badge_remover")
    channel.setMethodCallHandler(this)
    context = flutterPluginBinding.applicationContext
  }

  override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {

    Log.d("AndroidAppBadgeRemoverPlugin", "callMethod => ${call.method}")

    if (call.method == "appBadgeRemover") {
      NotificationManagerCompat.from(context).cancelAll()
      result.success(true)
    } else {
      result.notImplemented()
    }
  }

  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
  }

  override fun onAttachedToActivity(binding: ActivityPluginBinding) {
    // Activityがattachされたタイミングでメンバ変数にbindingされたActivityを格納
    activity = binding.activity
  }

  // 以下は書いておかないとエラーが出るので書いてるけど、今回は特に処理は不要
  override fun onDetachedFromActivityForConfigChanges() {}
  override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {}
  override fun onDetachedFromActivity() {}
}
