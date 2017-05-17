//
//  ViewController.swift
//  RecLoc
//
//  Created by Paril Shah on 2/4/17.
//  Copyright © 2017 Paril Shah. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UIPickerViewDelegate, UIPickerViewDataSource {

    @IBOutlet weak var btnInterestLocation:UIButton!
    @IBOutlet weak var barBtnDone:UIBarButtonItem!
    @IBOutlet weak var barBtnCancel:UIBarButtonItem!
    @IBOutlet weak var btnCountry:UIButton!
    @IBOutlet weak var pickerCountry:UIPickerView!
    @IBOutlet weak var toolBar:UIToolbar!

    var items = [Categories]()
    var isSelectionFromCountry = true
    var selectionOfInterest:Int = 0
    var countries: [String] = ["United States", "United Kingdom", "India", "China", "Japan", "France", "Egypt"]
    var selectedCountry:String!
    var selectedInterest:String!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        self.pickerCountry.isHidden = true
        self.toolBar.isHidden = true
        self.selectedCountry = countries.first
        //barBtnDone?.isEnabled = false
        self.btnInterestLocation.isEnabled = false
        self.navigationItem.rightBarButtonItem = barBtnDone
        self.navigationItem.leftBarButtonItem = barBtnCancel
        fetchCategories()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    func fetchCategories(){
    
        let rlInterestVM = RLInterestVM.init(urlString:Constant.baseURL+"category/fetchAllCategories", paramerts:nil, block:{(response:AnyObject)in
            for i in response as! Array<Categories>{
               // let catgory:Categories = i
                self.items.append(i)
            }
            self.btnInterestLocation.isEnabled = true
            self.selectedInterest = self.items.first?.categoryName
        })
        rlInterestVM.fetchCategories()
    }

    // MARK: UIPickerViewDataSource
    public func numberOfComponents(in pickerView: UIPickerView) -> Int{
        return 1
    }
    
    public func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int{
        if isSelectionFromCountry {
            return countries.count
        }else{
            return self.items.count
        }
    }
    
    // MARK: UIPickerViewDelegate
    public func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String?{
        if isSelectionFromCountry {
            return countries[row]
        } else {
            return self.items[row].categoryName
        }
    }
    
    public func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int){
        if isSelectionFromCountry{
            self.selectedCountry = countries[row]
        }else {
            self.selectedInterest = self.items[row].categoryName
            self.selectionOfInterest = row
        }
    }
    
    // MARK: Custom Methods
    @IBAction func pressCancel(){
        dismiss(animated: true, completion: nil)
    }
    
    @IBAction func pressDone(){
        if self.btnCountry.titleLabel?.text != "Select Country" && self.btnInterestLocation.titleLabel?.text != "Select Your Interest"{
            // Go to Search Result view Controller.
            let yourVC = self.storyboard?.instantiateViewController(withIdentifier: "RLResultViewController") as! RLResultViewController
            yourVC.locationString = self.selectedCountry
            yourVC.category = self.items[self.selectionOfInterest]
            self.navigationController?.pushViewController(yourVC, animated: true)
        }else{
            let alertController = UIAlertController(title: "RecLoc", message: "Please select appropriate Interest and Country.", preferredStyle: UIAlertControllerStyle.alert) //
            // Replace UIAlertActionStyle.Default by UIAlertActionStyle.default
            let okAction = UIAlertAction(title: "OK", style: UIAlertActionStyle.default) {
                (result : UIAlertAction) -> Void in

            }
            alertController.addAction(okAction)
            self.present(alertController, animated: true, completion: nil)
        }

        //dismiss(animated: true, completion: nil)
    }
    
    @IBAction func searchInterest(){
        isSelectionFromCountry = false
        self.pickerCountry.reloadAllComponents()
        UIView.animate(withDuration: 0.25, delay: 0.0, options: [.curveLinear], animations: {
            self.pickerCountry.isHidden = false
            self.toolBar.isHidden = false
        }, completion: { (finished: Bool) in
            
        })
    }
    
    @IBAction func searchCountry(){
        isSelectionFromCountry = true
        self.pickerCountry.reloadAllComponents()
        UIView.animate(withDuration: 0.25, delay: 0.0, options: [.curveLinear], animations: {
            self.pickerCountry.isHidden = false
            self.toolBar.isHidden = false
        }, completion: { (finished: Bool) in
            
        })
    }
    
    @IBAction func cancelToolBar(){
        UIView.animate(withDuration: 0.25, delay: 0.0, options: [.curveLinear], animations: {
            self.pickerCountry.isHidden = true
            self.toolBar.isHidden = true
        }, completion: { (finished: Bool) in
            
        })
    }
    
    @IBAction func selectCountry(){
        UIView.animate(withDuration: 0.25, delay: 0.0, options: [.curveLinear], animations: {
            self.pickerCountry.isHidden = true
            self.toolBar.isHidden = true
            if self.isSelectionFromCountry{
                self.btnCountry.setTitle(self.selectedCountry, for: .normal)
            }else{
                self.btnInterestLocation.setTitle(self.selectedInterest, for: .normal)
            }
        }, completion: { (finished: Bool) in

        })
    }
}


