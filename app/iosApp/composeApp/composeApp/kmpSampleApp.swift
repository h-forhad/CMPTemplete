import SwiftUI
import Favily

@main
struct kmpSampleApp: App {
  @UIApplicationDelegateAdaptor var delegate: AppDelegate
  @Environment(\.scenePhase)  var scenePhase: ScenePhase

  var defaultRouterContext: RouterContext { delegate.holder.defaultRouterContext }

  var body: some Scene {
    WindowGroup {
      ComposeView(routerContext: defaultRouterContext).ignoresSafeArea()
    }
    .onChange(of: scenePhase) { newPhase in
        switch newPhase {
        case .background: defaultRouterContext.stop()
        case .inactive: defaultRouterContext.pause()
        case .active: defaultRouterContext.resume()
        @unknown default: break
        }
    }
  }
}

class DefaultRouterHolder : ObservableObject {
  let defaultRouterContext: RouterContext = DefaultRouterContextKt.defaultRouterContext()

  deinit {
    // Destroy the root component before it is deallocated
    defaultRouterContext.destroy()
  }
}

class AppDelegate: NSObject, UIApplicationDelegate {
    let holder: DefaultRouterHolder = DefaultRouterHolder()
}

struct ComposeView: UIViewControllerRepresentable {
      let routerContext: RouterContext

      func makeUIViewController(context: Context) -> UIViewController {
        let controller = SampleAppViewControllerKt.createViewController(routerContext: routerContext)
        return controller
      }

      func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
      }
        
      private func setupStatusBar(view: UIView) {
        let statusBarColor = SampleAppViewControllerKt.getStatusBarColor()
        let statusbarView = UIApplication.shared.statusBarUIView
        statusbarView?.backgroundColor = statusBarColor
    }
}
