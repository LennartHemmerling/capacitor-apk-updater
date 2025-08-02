// swift-tools-version: 5.9
import PackageDescription

let package = Package(
    name: "CapacitorApkUpdater",
    platforms: [.iOS(.v14)],
    products: [
        .library(
            name: "CapacitorApkUpdater",
            targets: ["ApkUpdaterPlugin"])
    ],
    dependencies: [
        .package(url: "https://github.com/ionic-team/capacitor-swift-pm.git", from: "7.0.0")
    ],
    targets: [
        .target(
            name: "ApkUpdaterPlugin",
            dependencies: [
                .product(name: "Capacitor", package: "capacitor-swift-pm"),
                .product(name: "Cordova", package: "capacitor-swift-pm")
            ],
            path: "ios/Sources/ApkUpdaterPlugin"),
        .testTarget(
            name: "ApkUpdaterPluginTests",
            dependencies: ["ApkUpdaterPlugin"],
            path: "ios/Tests/ApkUpdaterPluginTests")
    ]
)