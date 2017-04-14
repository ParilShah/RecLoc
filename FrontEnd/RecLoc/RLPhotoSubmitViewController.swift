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
    
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        tagListView.delegate = self
        tagListView.alignment = .left // possible values are .Left, .Center, and .Right
        tagListView.addTag("Forest")
        tagListView.insertTag("Mountain", atIndex: 1)
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
        
        }
        
        let credentialProvider:AWSCognitoCredentialsProvider = AWSCognitoCredentialsProvider(regionType:.USEast2, identityPoolId:"us-west-2:cad15e21-3514-4df2-9b95-b5349963acfb")
        
        let configuration: AWSServiceConfiguration = AWSServiceConfiguration(region: .USEast2, credentialsProvider: credentialProvider)
        AWSServiceManager.default().defaultServiceConfiguration = configuration
        
        AWSRekognition.register(with:configuration, forKey:"USWest2Rekognition")
        let Rekognition = AWSRekognition(forKey:"USWest2Rekognition")
        
        let image = AWSRekognitionImage()
        image!.bytes = UIImageJPEGRepresentation(placeImage!, 0.7)
        
        guard let request = AWSRekognitionDetectLabelsRequest() else {
            puts("Unable to initialize AWSRekognitionDetectLabelsRequest.")
            return
        }
        
        request.image = image
        request.maxLabels = 3
        request.minConfidence = 60
        
        Rekognition.detectLabels(request) { (response:AWSRekognitionDetectLabelsResponse?, error:Error?) in
            print(response?.labels)
        }
        
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
        if (placemark != nil) {
            //stop updating location to save battery life
            locationManager.stopUpdatingLocation()
            print(placemark.addressDictionary!)
            
        }
    }
    
    // MARK: UITextViewDelegate
    public func textViewDidBeginEditing(_ textView: UITextView){
    
    }
    
    public func textViewDidEndEditing(_ textView: UITextView){
    
    }
    

}
