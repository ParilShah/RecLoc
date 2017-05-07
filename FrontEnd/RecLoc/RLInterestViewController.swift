//
//  RLInterestViewController.swift
//  RecLoc
//
//  Created by Paril Shah on 2/21/17.
//  Copyright © 2017 Paril Shah. All rights reserved.
//

import UIKit
import Alamofire
import SwiftyJSON
import NVActivityIndicatorView

class RLInterestViewController: UIViewController,UITableViewDelegate,UITableViewDataSource {
    @IBOutlet var tableView:UITableView?
    var items = [Categories]()
    var selectedRows = [Int]()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        self.tableView!.tableFooterView = UIView()
        fetchCategories()
        
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    // MARK: CUSTOM METHODS
    @IBAction func onPressDone(_ sender: Any?){
        let arry = getSelectedCategoriesArray()
//        let dic:[String: Any] = ["deviceId":"1237", "category":arry]
        let dic:[String: Any] = ["deviceId":"1234", "category":arry]
        let rlInterestVM = RLInterestVM.init(urlString:"http://localhost:8080/user/submitUser", paramerts:dic, block:{(response:AnyObject)in
            let s = response as! [String:Any]
            let code = s["responseCode"] as! Int
            if code == 200{
                UserDefaults.standard.set(arry, forKey: "Categories") // Category Objects
                UserDefaults.standard.set(dic["deviceId"], forKey: "User") // User Object
                let storyboard = UIStoryboard(name: "Main", bundle: nil)
                let controller = storyboard.instantiateViewController(withIdentifier: "RLTab")
                self.present(controller, animated: true, completion: nil)
            }
        })
        rlInterestVM.submitUser()
    }
    /*
    @IBAction func onPressDone(_ sender: Any?){
        //let arry = getSelectedCategoriesArray()
        let userDic:[String: String] = ["deviceId":"1042323"]
        let addrsDic:[String: String] = ["addressLine1":"","addressLine2":"","city":"","state":"","country":"","zip":"","latitude":"","longitude":""]
        let catArray:[String] = ["Snow","Mountaion"]
        let dicAddress:[String: Any] = ["locationName":"Florida","locationDescription":"Cool Place","Address":addrsDic, "tags":catArray]
        let dicFinal:[String: Any] = ["locationDetails":dicAddress]
        
        let dic:[String: Any] = ["user":userDic, "location":dicFinal]
        
        let request:RLNetworking = RLNetworking.init()
        request.fetchResponseUsingPost(url: "http://localhost:8080/location/submitLocation", parameters: dic, block:{(response:JSON) -> Void in
            print(response)
        })
    }
    */
    func fetchCategories(){
        // Below part is a Activity Indicator View which can be improved.
        let points = CGPoint(x: UIScreen.main.bounds.width/2.0 - 25, y: UIScreen.main.bounds.height/2.0 - 25)
        let activityIndicator =  NVActivityIndicatorView(frame: CGRect(x: points.x, y: points.y, width: 50, height: 50), type: .ballScaleMultiple, color: UIColor.init(colorLiteralRed: 216.0/255.0, green: 67.0/255.0, blue: 21.0/255.0, alpha: 1.0), padding: NVActivityIndicatorView.DEFAULT_PADDING)
        self.view.addSubview(activityIndicator)
        activityIndicator.startAnimating()
        
        let rlInterestVM = RLInterestVM.init(urlString:"http://localhost:8080/category/fetchAllCategories", paramerts:nil, block:{(response:AnyObject)in
            self.items = response as! [Categories]
            self.selectedRows = Array(repeating:0, count:self.items.count)
            self.tableView?.reloadData()
            activityIndicator.stopAnimating()
        })
        rlInterestVM.fetchCategories()
    }
    
    func getSelectedCategoriesArray()->[AnyObject]{
        let arryIndexPath = self.tableView?.indexPathsForSelectedRows
        var arryForCategories = [AnyObject]()
        for i in arryIndexPath!{
            let selectedCat = self.items[i.row] as Categories
            let dict:[String:AnyObject] = ["categoryId":selectedCat.categoryId as AnyObject, "categoryName":selectedCat.categoryName as AnyObject]
            arryForCategories.append(dict as AnyObject)
        }
        return arryForCategories
    }
    
}

extension RLInterestViewController {
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.items.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell:UITableViewCell = (self.tableView?.dequeueReusableCell(withIdentifier: "interestcell"))!
        let categories:Categories = items[indexPath.row] as Categories
        cell.textLabel?.text = categories.categoryName
        return cell
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        tableView.cellForRow(at: indexPath)?.accessoryType = UITableViewCellAccessoryType.checkmark
        self.selectedRows[indexPath.row] = 1;
        
    }
    
    func tableView(_ tableView: UITableView, didDeselectRowAt indexPath: IndexPath) {
        tableView.cellForRow(at: indexPath)?.accessoryType = UITableViewCellAccessoryType.none
        self.selectedRows[indexPath.row] = 0;
    }
}
