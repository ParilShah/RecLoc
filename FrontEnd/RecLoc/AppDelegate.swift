//
//  AppDelegate.swift
//  RecLoc
//
//  Created by Paril Shah on 2/4/17.
//  Copyright © 2017 Paril Shah. All rights reserved.
//

import UIKit
import GoogleMaps

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {

    var window: UIWindow?


    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplicationLaunchOptionsKey: Any]?) -> Bool {
        // Override point for customization after application launch.
        GMSServices.provideAPIKey("AIzaSyD9mNFQqvpbHnwYw5BNVp6jn_oes7zXcOI")
        UINavigationBar.appearance().barTintColor = UIColor(colorLiteralRed: 41/255, green: 128/255, blue: 185/255, alpha: 1)
        
        UINavigationBar.appearance().tintColor = UIColor.white
        UINavigationBar.appearance().titleTextAttributes = [NSForegroundColorAttributeName:UIColor.white]
        UITabBar.appearance().barTintColor = UIColor(colorLiteralRed: 41/255, green: 128/255, blue: 185/255, alpha: 1)
        UITabBar.appearance().tintColor = UIColor.white
        UITabBar.appearance().unselectedItemTintColor = UIColor.white
        
        
        //let user = UserDefaults.standard.object(forKey: "User") as! String
        if UserDefaults.standard.object(forKey: "User") != nil{
            let appDelegate = UIApplication.shared.delegate as! AppDelegate
            appDelegate.window = UIWindow(frame: UIScreen.main.bounds)
            
            let mainStoryboard: UIStoryboard = UIStoryboard(name: "Main", bundle: nil) // this assumes your storyboard is titled "Main.storyboard"
            let yourVC = mainStoryboard.instantiateViewController(withIdentifier: "RLTab") as! RLTabViewController // inside "YOUR_VC_IDENTIFIER" substitute the Storyboard ID you created in step 2 for the view controller you want to open here. And substitute YourViewController with the name of your view controller, like, for example, ViewController2.
            appDelegate.window?.rootViewController = yourVC
            appDelegate.window?.makeKeyAndVisible()
        }
        
        return true
    }

    func applicationWillResignActive(_ application: UIApplication) {
        // Sent when the application is about to move from active to inactive state. This can occur for certain types of temporary interruptions (such as an incoming phone call or SMS message) or when the user quits the application and it begins the transition to the background state.
        // Use this method to pause ongoing tasks, disable timers, and invalidate graphics rendering callbacks. Games should use this method to pause the game.
    }

    func applicationDidEnterBackground(_ application: UIApplication) {
        // Use this method to release shared resources, save user data, invalidate timers, and store enough application state information to restore your application to its current state in case it is terminated later.
        // If your application supports background execution, this method is called instead of applicationWillTerminate: when the user quits.
    }

    func applicationWillEnterForeground(_ application: UIApplication) {
        // Called as part of the transition from the background to the active state; here you can undo many of the changes made on entering the background.
    }

    func applicationDidBecomeActive(_ application: UIApplication) {
        // Restart any tasks that were paused (or not yet started) while the application was inactive. If the application was previously in the background, optionally refresh the user interface.
    }

    func applicationWillTerminate(_ application: UIApplication) {
        // Called when the application is about to terminate. Save data if appropriate. See also applicationDidEnterBackground:.
    }


}

