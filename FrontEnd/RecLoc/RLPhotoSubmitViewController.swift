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


class RLPhotoSubmitViewController: UIViewController, TagListViewDelegate, CLLocationManagerDelegate, UITextViewDelegate{
    
    public var placeImage:UIImage!
    public var locationFromGalleryImage : CLLocationCoordinate2D?
    let locationManager = CLLocationManager()
    var isFromGallery:Bool = false
    
    var uploadButton: UIBarButtonItem!
    @IBOutlet weak var tagListView:TagListView!
    @IBOutlet weak var placeImageView:UIImageView!
    @IBOutlet weak var placeHolderLabel:UILabel!
    @IBOutlet weak var descriptionTxtView:UITextView!
    @IBOutlet weak var locationLbl:UILabel!
    
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        tagListView.delegate = self
        tagListView.alignment = .left // possible values are .Left, .Center, and .Right
        uploadButton = UIBarButtonItem.init(title: "Upload", style: .plain, target: self, action: #selector(pressUpload(Sender:)))
        self.navigationItem.rightBarButtonItem = uploadButton
        self.placeImageView!.image = placeImage
        
        // TODO: When the photo is taken from Camera, then use the below and move it in the function.
        // Configuration for getting CoreLocation        
        if(!isFromGallery){
            locationManager.delegate = self
            locationManager.desiredAccuracy = kCLLocationAccuracyBest
            locationManager.requestWhenInUseAuthorization()
            locationManager.startUpdatingLocation()
        }else{
//            let getLat: CLLocationDegrees = (self.locationFromGalleryImage?.latitude)!
//            let getLon: CLLocationDegrees = (self.locationFromGalleryImage?.longitude)!
//            let getMovedMapCenter: CLLocation =  CLLocation(latitude: getLat, longitude: getLon)
//            
//            CLGeocoder().reverseGeocodeLocation(getMovedMapCenter, completionHandler: {(placemarks, error)-> Void in
//                
//                if (error != nil) {
//                    print("Reverse geocoder failed with error" + (error?.localizedDescription)!)
//                    return
//                }
//                
//                if (placemarks?.count)! > 0 {
//                    let pm = placemarks?.first
//                    self.displayLocationInfo(placemark: pm!)
//                } else {
//                    print("Problem with the data received from geocoder")
//                }
//            })
        }
        
        let instanceOfCustomObject: RLPhotoManipulate = RLPhotoManipulate.init(block: {(response: Any?, error:Error?)in
            if (error == nil){
                print(response!)
                DispatchQueue.main.async {
                    for object in (response as? Array<Any>)!{
                        let jsonResult = object as! AWSRekognitionLabel
                        //print(jsonResult.name)
                        self.tagListView.addTag(jsonResult.name!)
                    }
                }
            }
        })
        instanceOfCustomObject.returnDictionary(placeImage!)
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    // MARK: - TagListViewDelegate
    func tagPressed(_ title: String, tagView: TagView, sender: TagListView) {
        print("Tag pressed: \(title), \(sender)")
    }
    
    func pressUpload(Sender: Any?) {
        print("Upload")
    }
    
    // MARK: - CLLocationManagerDelegate
    public func locationManager(_ manager: CLLocationManager, didUpdateLocations locations: [CLLocation]){
        CLGeocoder().reverseGeocodeLocation(manager.location!, completionHandler: {(placemarks, error)-> Void in
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
    }
    
    public func locationManager(_ manager: CLLocationManager, didFailWithError error: Error){
        print("Error while updating location " + error.localizedDescription)
    }
    
    // MARK: Custom Methods
    func displayLocationInfo(placemark: CLPlacemark) {
        
            //stop updating location to save battery life
            locationManager.stopUpdatingLocation()
            print(placemark.addressDictionary!)
            self.locationLbl.text = (placemark.name!+","+placemark.country!)
        
    }
    
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
