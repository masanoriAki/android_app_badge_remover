#import "AndroidAppBadgeRemoverPlugin.h"
#if __has_include(<android_app_badge_remover/android_app_badge_remover-Swift.h>)
#import <android_app_badge_remover/android_app_badge_remover-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "android_app_badge_remover-Swift.h"
#endif

@implementation AndroidAppBadgeRemoverPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftAndroidAppBadgeRemoverPlugin registerWithRegistrar:registrar];
}
@end
