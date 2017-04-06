//
//  RLLocationDetailViewController.swift
//  RecLoc
//
//  Created by Paril Shah on 4/6/17.
//  Copyright © 2017 Paril Shah. All rights reserved.
//

import UIKit
import GoogleMaps
import TagListView

class RLLocationDetailViewController: UIViewController {
    public var placeName:String?
    public var placeImage:UIImage?
    public var placeDescription:String?
    public var placeTags:Array<String>?
    public var placeLocation:CLLocationCoordinate2D?
    public var placeLat:Double?
    public var placeLang:Double?

    
    @IBOutlet weak var placeImageView: UIImageView!
    @IBOutlet weak var placeNameLabel: UITextView!
    @IBOutlet weak var tagListView:TagListView!
    @IBOutlet weak var mapView:UIView!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
        self.navigationItem.title = self.placeName
        self.placeImageView?.image = placeImage
        for name in placeTags! {
            self.tagListView?.addTag(name)
        }
        self.placeNameLabel.text = placeDescription
        
        // Create a GMSCameraPosition that tells the map to display the
        // coordinate -33.86,151.20 at zoom level 6.
        let camera = GMSCameraPosition.camera(withLatitude: placeLat!, longitude: placeLang!, zoom: 6.0)
        let mapView2 = GMSMapView.map(withFrame: self.mapView.bounds, camera: camera)
        self.mapView?.addSubview(mapView2)
        
        // Creates a marker in the center of the map.
        let marker = GMSMarker()
        marker.position = self.placeLocation!
        marker.title = "Sydney"
        marker.snippet = "Australia"
        marker.map = mapView2

    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
}
