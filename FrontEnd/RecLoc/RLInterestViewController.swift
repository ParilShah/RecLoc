//
//  RLInterestViewController.swift
//  RecLoc
//
//  Created by Paril Shah on 2/21/17.
//  Copyright © 2017 Paril Shah. All rights reserved.
//

import UIKit
import Alamofire

class RLInterestViewController: UIViewController,UITableViewDelegate,UITableViewDataSource {
    @IBOutlet var tableView:UITableView?
    var items: [String] = ["Mountains", "Beach", "Forest", "lake","City"]
    var selectedRows:[Int] = []
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        self.selectedRows = Array(repeating:0, count:items.count)
        self.tableView!.tableFooterView = UIView()

        // Testing
//        Alamofire.request("http://localhost:8080/greeting?name=paril").responseJSON { response in
//            debugPrint(response)
//            
//            if let json = response.result.value {
//                print("JSON: \(json)")
//            }
//        }
        
        // TODO: Testing POST
    
//        Alamofire.request("http://localhost:8080/searchCategory", method: .post, parameters: ["searchCategoryRequest":["categoryName": "bar", "deviceId":"3232-2323123-2313123"]],encoding: JSONEncoding.default, headers: nil).responseJSON {
//            response in
//            switch response.result {
//            case .success:
//                print(response)
//                
//                break
//            case .failure(let error):
//                
//                print(error)
//            }
//        }
        
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.items.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell:UITableViewCell = (self.tableView?.dequeueReusableCell(withIdentifier: "interestcell"))!
        cell.textLabel?.text = items[indexPath.row]
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
    
    @IBAction func onPressDone(_ sender: Any?){
        let storyboard = UIStoryboard(name: "Main", bundle: nil)
        let controller = storyboard.instantiateViewController(withIdentifier: "RLTab")
        self.present(controller, animated: true, completion: nil)
    }

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */
}
