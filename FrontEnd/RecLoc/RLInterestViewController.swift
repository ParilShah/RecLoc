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
import DZNEmptyDataSet
import NVActivityIndicatorView

class RLInterestViewController: UIViewController, UITableViewDelegate, UITableViewDataSource, DZNEmptyDataSetSource, DZNEmptyDataSetDelegate{
    @IBOutlet var tableView:UITableView?
    var nextButton:UIBarButtonItem!
    var selectedRowCount:Int = 0
    var items = [Categories]()
    var selectedRows = [Int]()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        self.tableView!.emptyDataSetSource = self
        self.tableView!.emptyDataSetDelegate = self
        self.tableView!.tableFooterView = UIView()
        self.tableView!.sectionHeaderHeight = 70
        
        nextButton = UIBarButtonItem.init(title: "Done", style: .plain, target: self, action: #selector(onPressDone(Sender:)))
        self.navigationItem.rightBarButtonItem = nextButton
        nextButton.isEnabled = false
        fetchCategories()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    // MARK: CUSTOM METHODS
    @IBAction func onPressDone(Sender: Any?){
        let arry = getSelectedCategoriesArray()
        let uuid = NSUUID().uuidString
//        let dic:[String: Any] = ["deviceId":"1237", "category":arry]
        let dic:[String: Any] = ["deviceId":uuid, "category":arry]
        let rlInterestVM = RLInterestVM.init(urlString: Constant.baseURL + "user/submitUser", paramerts:dic, block:{(response:AnyObject)in
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
    
    func fetchCategories(){
        // Below part is a Activity Indicator View which can be improved.
        let points = CGPoint(x: UIScreen.main.bounds.width/2.0 - 25, y: UIScreen.main.bounds.height/2.0 - 25)
        let activityIndicator =  NVActivityIndicatorView(frame: CGRect(x: points.x, y: points.y, width: 50, height: 50), type: .ballScaleMultiple, color: UIColor.init(colorLiteralRed: 216.0/255.0, green: 67.0/255.0, blue: 21.0/255.0, alpha: 1.0), padding: NVActivityIndicatorView.DEFAULT_PADDING)
        self.view.addSubview(activityIndicator)
        activityIndicator.startAnimating()
        let rlInterestVM = RLInterestVM.init(urlString:Constant.baseURL+"category/fetchAllCategories", paramerts:nil, block:{(response:AnyObject)in
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
    
    func statusOfTheBarButton(){
        nextButton.isEnabled = selectedRowCount == 0 ? false:true
    }
    
    // MARK: - DZNEmptyDataSetSource
    func title(forEmptyDataSet scrollView: UIScrollView!) -> NSAttributedString! {
        let text = "RecLoc"
        let attributes = [
            NSFontAttributeName: UIFont.boldSystemFont(ofSize: 18.0)
        ]
        return NSAttributedString(string: text, attributes: attributes)
    }
    
    func description(forEmptyDataSet scrollView: UIScrollView!) -> NSAttributedString! {
        let text = "Interests will be appeared soon"
        let paragraph = NSMutableParagraphStyle()
        paragraph.lineBreakMode = .byWordWrapping
        paragraph.alignment = .center
        
        let attributes = [
            NSFontAttributeName: UIFont.systemFont(ofSize: 14.0),
            NSParagraphStyleAttributeName: paragraph
        ]
        
        return NSAttributedString(string: text, attributes: attributes)
    }
    
    func image(forEmptyDataSet scrollView: UIScrollView!) -> UIImage! {
        return UIImage(named: "cracked-egg")
    }
    
    func backgroundColor(forEmptyDataSet scrollView: UIScrollView!) -> UIColor! {
        return UIColor.init(red: 236.0/255.0, green: 240.0/255.0, blue: 241.0/255.0, alpha: 1.0)
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
        selectedRowCount += 1
        statusOfTheBarButton()
    }
    
    func tableView(_ tableView: UITableView, didDeselectRowAt indexPath: IndexPath) {
        tableView.cellForRow(at: indexPath)?.accessoryType = UITableViewCellAccessoryType.none
        self.selectedRows[indexPath.row] = 0;
        selectedRowCount -= 1
        statusOfTheBarButton()
    }
    
    func tableView(tableView: UITableView, viewForHeaderInSection section: Int) -> UIView? {
        let vw = UIView()
        vw.backgroundColor = UIColor.init(red: 236.0/255.0, green: 240.0/255.0, blue: 241.0/255.0, alpha: 1.0)
        let lblHeader: UILabel = UILabel.init()
        lblHeader.numberOfLines = 0;
        lblHeader.lineBreakMode = .byWordWrapping
        lblHeader.text = "Your Location Suggestions will be appeared on the basis of your selections."
        vw.addSubview(lblHeader)
        return vw
    }

    public func tableView(_ tableView: UITableView, viewForHeaderInSection section: Int) -> UIView?{
        let vw:UIView = UIView.init(frame: CGRect(x: 0, y: 0, width: self.view.bounds.size.width, height: 70))
        vw.backgroundColor = UIColor.init(red: 236.0/255.0, green: 240.0/255.0, blue: 241.0/255.0, alpha: 1.0)
        let lblHeader: UILabel = UILabel.init(frame: CGRect(x: 10, y: 10, width: self.view.bounds.size.width-10, height: 60))
        lblHeader.numberOfLines = 0;
        lblHeader.lineBreakMode = .byWordWrapping
        lblHeader.text = "Your Location Suggestions will be appeared on the basis of your selections."
        lblHeader.textColor = UIColor.darkText
        lblHeader.font = UIFont.boldSystemFont(ofSize: 14.0)
        vw.addSubview(lblHeader)
        return vw
    }
}
