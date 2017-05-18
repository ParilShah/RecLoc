//
//  RLPhotoSubmitViewController.swift
//  RecLoc
//
//  Created by Paril Shah on 4/5/17.
//  Copyright © 2017 Paril Shah. All rights reserved.
//

import UIKit
import TagListView
import CoreLocation
import AWSRekognition
import SwiftyJSON

class RLPhotoSubmitViewController: UIViewController, TagListViewDelegate, CLLocationManagerDelegate, UITextViewDelegate, UITextFieldDelegate{
    
    public var placeImage:UIImage!
    public var locationFromGalleryImage : CLLocationCoordinate2D?
    let locationManager = CLLocationManager()
    var isFromGallery:Bool = false
    
    var uploadButton: UIBarButtonItem!
    var locationMark:CLPlacemark?
    var tags:[String]=[]
    
    @IBOutlet weak var locationLbl:UILabel!
    @IBOutlet weak var tagListView:TagListView!
    @IBOutlet weak var placeHolderLabel:UILabel!
    @IBOutlet weak var placeImageView:UIImageView!
    @IBOutlet weak var locationTxtField:UITextField!
    @IBOutlet weak var descriptionTxtView:UITextView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        tagListView.delegate = self
        tagListView.alignment = .left
        uploadButton = UIBarButtonItem.init(title: "Upload", style: .plain, target: self, action: #selector(pressUpload(Sender:)))
        self.navigationItem.rightBarButtonItem = uploadButton
        self.navigationItem.rightBarButtonItem?.isEnabled = false
        self.placeImageView!.image = placeImage
        self.descriptionTxtView.layer.cornerRadius = 5.0
        self.navigationItem.title = "Uplod Location"
        // configuration for the screen.
        configurationForLocation()
        fetchTagForImageFromAWS()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    // MARK: - TagListViewDelegate
    func tagPressed(_ title: String, tagView: TagView, sender: TagListView) {
        print("Tag pressed: \(title), \(sender)")
    }
    
    // MARK: Custom Methods
    func configurationForLocation(){
        // TODO: When the photo is taken from Camera, then use the below and move it in the function.
        // Configuration for getting CoreLocation
        if(!isFromGallery){
            locationManager.delegate = self
            locationManager.desiredAccuracy = kCLLocationAccuracyBest
            locationManager.requestWhenInUseAuthorization()
            locationManager.startUpdatingLocation()
        }else{
            if (self.locationFromGalleryImage?.latitude) != nil{
                let getLat: CLLocationDegrees = (self.locationFromGalleryImage?.latitude)!
                let getLon: CLLocationDegrees = (self.locationFromGalleryImage?.longitude)!
                let getMovedMapCenter: CLLocation =  CLLocation(latitude: getLat, longitude: getLon)
                
                CLGeocoder().reverseGeocodeLocation(getMovedMapCenter, completionHandler: {(placemarks, error)-> Void in
                    
                    if (error != nil) {
                        print("Reverse geocoder failed with error" + (error?.localizedDescription)!)
                        return
                    }
                    
                    if (placemarks?.count)! > 0 {
                        let pm = placemarks?.first
                        self.displayLocationInfo(placemark: pm!)
                    } else {
                        print("Problem with the data received from geocoder")
                    }
                })
            }else{
                self.locationLbl.text = "CSUS, Sacramento, California, United States"
            }
            
        }
    
    }
    
    func fetchTagForImageFromAWS(){
        
        let instanceOfCustomObject: RLPhotoManipulate = RLPhotoManipulate.init(block: {(response: Any?, error:Error?)in
            if (error == nil){
                print(response!)
                DispatchQueue.main.async {
                    for object in (response as? Array<Any>)!{
                        let jsonResult = object as! AWSRekognitionLabel
                        self.tagListView.addTag(jsonResult.name!)
                        self.tags.append(jsonResult.name!)
                        self.changeBarButtonStatus()
                    }
                }
            }
        })
        instanceOfCustomObject.returnDictionary(placeImage!)
    }
    
    func pressUpload(Sender: Any?) {
        let locationDic:[String: String] = getLatitudeAndLongitude()
        let userDic:[String: String] = ["deviceId":UserDefaults.standard.object(forKey: "User") as! String]
        let addrsDic:[String: String?]
        if (self.locationMark != nil){
            addrsDic = ["addressLine1":self.locationMark!.thoroughfare,"addressLine2":"","city":self.locationMark!.locality,"state":self.locationMark!.administrativeArea,"country":self.locationMark!.country!,"zip":self.locationMark!.postalCode,"latitude":locationDic["latitude"]!,"longitude":locationDic["longitude"]!]
        } else {
            addrsDic = ["addressLine1":"6000 J Street","addressLine2":"","city":"Sacramento","state":"California","country":"United States","zip":"95825","latitude":locationDic["latitude"]!,"longitude":locationDic["longitude"]!]
        
        }
        
        let tags:[String] = self.tags
        let dicAddress:[String: Any] = ["locationName":locationTxtField.text as Any,"locationDescription":descriptionTxtView.text as Any,"Address":addrsDic, "tags":tags]
        let dicLocation:[String: Any] = ["locationDetails":dicAddress]
        
        let dicUserAndLocation:[String: Any] = ["user":userDic, "location":dicLocation]
        let parameters:[String: Any] = ["jsonRequest":dicUserAndLocation]
        let imageData = UIImageJPEGRepresentation(placeImageView.image!, 0.2)!
        let imageToUpload = UIImage(data: imageData)
        let request:RLNetworking = RLNetworking.init()
        request.uploadImageUsingPost(url: Constant.baseURL+"location/submitLocation",image:imageToUpload!,parameters: parameters["jsonRequest"], block:{(response:JSON) -> Void in
            print(response)
            
        })
        self.navigationController?.popToRootViewController(animated: true)
        self.tabBarController?.selectedIndex = 0
    }
    
    func getLatitudeAndLongitude()->[String:String]{
        var latString: String!
        var longString: String!
        if(!isFromGallery){
            let latitude = self.locationManager.location?.coordinate.latitude
            let longitude = self.locationManager.location?.coordinate.longitude
            latString = String(format: "%f", latitude!)
            longString = String(format: "%f", longitude!)
            
        } else {
            if (locationFromGalleryImage == nil) {
                latString = "38.5604"
                longString = "-121.4241"
            }else{
                latString = String(format: "%f", (self.locationFromGalleryImage?.latitude)!)
                longString = String(format: "%f", (self.locationFromGalleryImage?.longitude)!)
            }
        }
        return ["latitude":latString,"longitude":longString]
    }
    
    func changeBarButtonStatus(){
        if self.locationTxtField.text?.characters.count != 0 && self.tags.count != 0 {
            self.navigationItem.rightBarButtonItem?.isEnabled = true
        }else{
            self.navigationItem.rightBarButtonItem?.isEnabled = false
        }
    
    }
}

extension RLPhotoSubmitViewController {
    // MARK: UITextViewDelegate
    public func textViewDidBeginEditing(_ textView: UITextView){
        self.placeHolderLabel.isHidden = true
    }
    
    public func textViewDidEndEditing(_ textView: UITextView){
        if (textView.text.characters.count == 0){
            self.placeHolderLabel.isHidden = false
        }else{
            self.placeHolderLabel.isHidden = true
        }
    }
    
    public func textView(_ textView: UITextView, shouldChangeTextIn range: NSRange, replacementText text: String) -> Bool{
        if text.characters.contains("\n"){
            textView.resignFirstResponder()
        }
        return true
    }

}

extension RLPhotoSubmitViewController {
    public func textFieldDidEndEditing(_ textField: UITextField){
        changeBarButtonStatus()
    }
    
    public func textFieldShouldReturn(_ textField: UITextField) -> Bool{
        textField.resignFirstResponder()
        return true
    }
}

extension RLPhotoSubmitViewController {
    // MARK: - CLLocationManagerDelegate
    public func locationManager(_ manager: CLLocationManager, didUpdateLocations locations: [CLLocation]){
        CLGeocoder().reverseGeocodeLocation(manager.location!, completionHandler: {(placemarks, error)-> Void in
            if (error != nil) {
                print("Reverse geocoder failed with error" + (error?.localizedDescription)!)
                return
            }
            
            if (placemarks?.count)! > 0 {
                let locMark = placemarks?.first
                self.displayLocationInfo(placemark: locMark!)
            } else {
                print("Problem with the data received from geocoder")
            }
        })
    }
    
    public func locationManager(_ manager: CLLocationManager, didFailWithError error: Error){
        print("Error while updating location " + error.localizedDescription)
    }
    
    // MARK: CUSTOM METHOD FOR LOCATION
    func displayLocationInfo(placemark: CLPlacemark) {
        locationManager.stopUpdatingLocation()
        print(placemark.addressDictionary!)
        self.locationMark = placemark
        self.locationLbl.text = (placemark.name!+","+placemark.country!)
    }
}
