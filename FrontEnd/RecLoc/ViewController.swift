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
    @IBOutlet weak var btnCountry:UIButton!
    @IBOutlet weak var pickerCountry:UIPickerView!
    @IBOutlet weak var toolBar:UIToolbar!
    
    var countries: [String] = ["US", "UK", "UAE", "India", "China"]
    var selectedCountry:String!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        self.pickerCountry.isHidden = true
        self.toolBar.isHidden = true
        self.selectedCountry = countries[0]
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


    // MARK: UIPickerViewDataSource
    public func numberOfComponents(in pickerView: UIPickerView) -> Int{
        return 1
    }
    
    public func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int{
        return countries.count
    }
    
    // MARK: UIPickerViewDelegate
    public func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String?{
        return countries[row]
    }
    
    public func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int){
        self.selectedCountry = countries[row]
    }
    
    // MARK: Custom Methods
    @IBAction func pressCancel(){
        dismiss(animated: true, completion: nil)
    }
    
    @IBAction func pressDone(){
        dismiss(animated: true, completion: nil)
    }
    
    @IBAction func searchInterest(){
        
    }
    
    @IBAction func searchCountry(){
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
            self.btnCountry.setTitle(self.selectedCountry, for: .normal)
        }, completion: { (finished: Bool) in
            
        })
    }
}


